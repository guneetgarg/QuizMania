package com.utilities;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CronExecution {

    final String buildNumber;
    String consoleOutput = "";
    InputStream in = null;
    Session session = null;
    Channel channel = null;

    /**
     * Constructor used to get latest build Number deployed on server
     */
    public CronExecution() {
        session = createSession();
        in = createChannel(session, Environment.value.getBuildCommand());
        consoleOutput = getConsoleLog(in);
        flushConnection();

        buildNumber = consoleOutput.substring(consoleOutput.indexOf(Environment.value.getCronEnv() + " ->"), consoleOutput.lastIndexOf("-" + Environment.value.getCronEnv() + "/public")).replace("intqa ->", "").trim();
        System.out.println(buildNumber);
    }

    /**
     * @return session created by login to server
     */
    public Session createSession() {
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        if (!(session == null) && session.isConnected() == true) {
            System.out.println("Session exist");
            return session;
        }
        try {
            session = jsch.getSession(Environment.value.getServerUser(), Environment.value.getServerHost(), 22);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        session.setPassword(Environment.value.getServerPassword());
        session.setConfig(config);
        try {
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        System.out.println("Connected");
        return session;
    }

    /**
     * @param session  created by login to server
     * @param command1 execution of command
     * @return input stream contains console log of executed command
     */
    public InputStream createChannel(Session session, String command1) {
        System.out.println(command1);
        try {
            channel = session.openChannel("exec");
        } catch (JSchException e) {
            e.printStackTrace();
        }
        ((ChannelExec) channel).setCommand(command1);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        try {
            in = channel.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * Closing the server connection
     */
    public void flushConnection() {
        channel.disconnect();
        session.disconnect();
    }


    /**
     * @param in contains console log of executed command
     * @return storing console log in string
     */
    public String getConsoleLog(InputStream in) {
        try {
            byte[] tmp = new byte[2024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 2024);
                    if (i < 0)
                        break;
                    //System.out.print(new String(tmp, 0, i));
                    consoleOutput += new String(tmp, 0, i);
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: "
                            + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consoleOutput;
    }

}