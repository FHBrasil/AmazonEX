package com.pixi.api.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.pixi.api.constants.PixiapiConstants;

import de.hybris.platform.util.Config;

/**
 * Connects and send files to FTP server.
 * 
 * @author franthescolly
 * @author jfelipe
 *
 */
public class FtpConnector {

    private static final Logger LOG = Logger.getLogger(FtpConnector.class.getName());
    private FTPClient ftpClient;


    /**
     * 
     */
    public FtpConnector() {
    }


    /**
     * Stores the given file in its respective profile (ftp server and directory).
     * 
     * @param file
     *            The file to be stored
     * @return
     *         true if the file was stored
     *         false otherwise
     *         true (special case) in case it is a dummy FTP Address
     *
     * @author jfelipe
     */
    public boolean storeFile(File file) {
        if (Boolean.valueOf(Config.getParameter(PixiapiConstants.PIXIAPI_USE_DUMMY_FTP))
                .booleanValue()) {
            LOG.info("Using dummy FTP Address: uploading file " + file.getName());
            return true;
        }
        try {
            List<FtpProfile> ftpProfileChain = getFTPProfileChain(file);
            if (ftpProfileChain == null || ftpProfileChain.isEmpty()) {
                LOG.error("Couldn't find a valid profile chain for: " + file.getName());
                return false;
            }
            for (FtpProfile profile : ftpProfileChain) {
                if (!connect(profile)) {
                    continue;
                }
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                for (String profileFolder : profile.getFolders()) {
                    ftpClient.cwd(profileFolder);
                }
                ftpClient.storeFile(file.getName(), new FileInputStream(file));
                LOG.info("FTP Reply string storing file " + file.getName() + " on "
                        + profile.getFtpAddress() + " : " + ftpClient.getReplyString());
            }
            return true;
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        } finally {
            disconnect();
        }
        return false;
    }


    /**
     * Connect to the given profile
     * 
     * @param profile
     *            Where to connect
     * @return
     *         true if connection was ok
     *         false otherwise
     * @author jfelipe
     */
    private boolean connect(FtpProfile profile) {
        if (Boolean.valueOf(Config.getParameter(PixiapiConstants.PIXIAPI_USE_DUMMY_FTP))
                .booleanValue()) {
            LOG.info("Using dummy FTP Address: connected");
            return true;
        }
        try {
            if (profile == null) {
                throw new InvalidParameterException("profile is null!");
            }
            disconnect();
            ftpClient = new FTPClient();
            ftpClient.connect(profile.getFtpAddress(), 21);
            ftpClient.enterLocalPassiveMode();
            ftpClient.login(profile.getFtpUserName(), profile.getFtpUserPassword());
        } catch (IOException ioe) {
            LOG.error("Could not log on FTP server: " + profile.getFtpAddress(), ioe);
            return false;
        }
        if (ftpClient.isConnected()) {
            LOG.info("Connected to " + profile.getFtpAddress());
        } else {
            LOG.error("Couldn't connect to " + profile.getFtpAddress());
        }
        return ftpClient.isConnected();
    }


    /**
     * Disconnects from the FTP client
     *
     * @author jfelipe
     */
    private void disconnect() {
        if (Boolean.valueOf(Config.getParameter(PixiapiConstants.PIXIAPI_USE_DUMMY_FTP))
                .booleanValue()) {
            LOG.info("Using dummy FTP Address: disconnected");
            return;
        }
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException ioe) {
                // nothing to do in this case
            }
        }
    }


    /**
     * Lists all the file profiles (ftp servers and directories) where it needs to be stored.
     * 
     * @param file
     *            The file which will choose which profile to load
     * @return
     *         List of profiles to send the given files
     * @author jfelipe
     */
    private List<FtpProfile> getFTPProfileChain(final File file) {
        if (Boolean.valueOf(Config.getParameter(PixiapiConstants.PIXIAPI_USE_DUMMY_FTP))
                .booleanValue()) {
            LOG.info("Using dummy FTP Address: creating FTP profile chain...");
            return Collections.EMPTY_LIST;
        }
        List<FtpProfile> profiles = new ArrayList<FtpProfile>();
        if (file.getName().equalsIgnoreCase(PixiapiConstants.PIXIAPI_FTP_FILE_VEDE)) {
            profiles.add(getProfileNrz());
            profiles.add(getProfileServer5());
        }
        return profiles;
    }


    /**
     * @return
     *         The profile for NRZ host
     * @author jfelipe
     */
    private FtpProfile getProfileNrz() {
        FtpProfile profile = new FtpProfile();
        profile.setFtpAddress(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_NRZ_ADDRESS));
        profile.setFtpUserName(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_NRZ_USERNAME));
        profile.setFtpUserPassword(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_NRZ_PASSWORD));
        profile.setFolders(new String[] { PixiapiConstants.PIXIAPI_FTP_FOLDER_CUSTOMERS,
                PixiapiConstants.PIXIAPI_FTP_FOLDER_380856090,
                PixiapiConstants.PIXIAPI_FTP_FOLDER_IN });
        return profile;
    }


    /**
     * @return
     *         The profile for server5 host
     * @author jfelipe
     */
    private FtpProfile getProfileServer5() {
        FtpProfile profile = new FtpProfile();
        profile.setFtpAddress(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_SERVER5_ADDRESS));
        profile.setFtpUserName(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_SERVER5_USERNAME));
        profile.setFtpUserPassword(Config
                .getParameter(PixiapiConstants.PIXIAPI_FTP_PROFILE_SERVER5_PASSWORD));
        profile.setFolders(new String[] {
                Config.getParameter(PixiapiConstants.PIXIAPI_FTP_FOLDER_LIFERANT),
                Config.getParameter(PixiapiConstants.PIXIAPI_FTP_FOLDER_VEDES) });
        return profile;
    }
}