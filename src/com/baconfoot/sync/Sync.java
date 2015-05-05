/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baconfoot.sync;

/**
 *
 * @author McLarenCm
 */
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Sync {

    private static List<String> listFilesResutls;
    private static List<String> listDirResutls;

    public static void main(String argv[]) throws Exception {

        Config conf = new Config();
        String listFiles[];
        listFiles = new String[1];
        listFiles[0] = "smb://localhost/c$/test/";
        listFilesResutls = listFiles(listFiles);

//        String getfiles[];
//        getfiles = new String[1];
//        getfiles[0] = "smb://localhost/c$/temp/file1.txt";
        for (String file : listFilesResutls) {
            String fileName = "smb://localhost/c$/test/" + file;
            getFile(fileName);

        }
//        

//        String putfiles[];
//        putfiles = new String[2];
//        putfiles[0] = "smb://localhost/c$/temp/file1.txt";
//        putfiles[1] = "smb://localhost/c$/temp/file2.txt";
//        for (String file : putfiles) {
//            putFile(file);
//        }
    }

    public static void getFile(String argv[]) throws Exception {

        //SmbFile f = new SmbFile(argv[0]);
        SmbFile file = new SmbFile(argv[0]);
        SmbFileInputStream in = new SmbFileInputStream(file);
        FileOutputStream out = new FileOutputStream(file.getName());

        long t0 = System.currentTimeMillis();

        byte[] b = new byte[8192];
        int n, tot = 0;
        long t1 = t0;
        while ((n = in.read(b)) > 0) {
            out.write(b, 0, n);
            tot += n;
            System.out.print('#');
        }

        long t = System.currentTimeMillis() - t0;

        System.out.println();
        System.out.println(tot + " bytes transfered in " + (t / 1000) + " seconds at " + ((tot / 1000) / Math.max(1, (t / 1000))) + "Kbytes/sec");

        in.close();
        out.close();
    }

    public static void getFile(String argv) throws Exception {

        //SmbFile f = new SmbFile(argv[0]);
        SmbFile file = new SmbFile(argv);
        SmbFileInputStream in = new SmbFileInputStream(file);
        FileOutputStream out = new FileOutputStream(file.getName());

        long t0 = System.currentTimeMillis();

        byte[] b = new byte[8192];
        int n, tot = 0;
        long t1 = t0;
        while ((n = in.read(b)) > 0) {
            out.write(b, 0, n);
            tot += n;
            System.out.print('#');
        }

        long t = System.currentTimeMillis() - t0;

        System.out.println();
        System.out.println(tot + " bytes transfered in " + (t / 1000) + " seconds at " + ((tot / 1000) / Math.max(1, (t / 1000))) + "Kbytes/sec");

        in.close();
        out.close();
    }

    public static void putFile(String argv[]) throws Exception {

        SmbFile f = new SmbFile(argv[0]);
        FileInputStream in = new FileInputStream(f.getName());
        SmbFileOutputStream out = new SmbFileOutputStream(f);

        long t0 = System.currentTimeMillis();

        byte[] b = new byte[8192];
        int n, tot = 0;
        while ((n = in.read(b)) > 0) {
            out.write(b, 0, n);
            tot += n;
            System.out.print('#');
        }

        long t = System.currentTimeMillis() - t0;

        System.out.println();
        System.out.println(tot + " bytes transfered in " + (t / 1000) + " seconds at " + ((tot / 1000) / Math.max(1, (t / 1000))) + "Kbytes/sec");

        in.close();
        out.close();
    }

    public static void putFile(String argv) throws Exception {

        SmbFile f = new SmbFile(argv);
        FileInputStream in = new FileInputStream(f.getName());
        SmbFileOutputStream out = new SmbFileOutputStream(f);

        long t0 = System.currentTimeMillis();

        byte[] b = new byte[8192];
        int n, tot = 0;
        while ((n = in.read(b)) > 0) {
            out.write(b, 0, n);
            tot += n;
            System.out.print('#');
        }

        long t = System.currentTimeMillis() - t0;

        System.out.println();
        System.out.println(tot + " bytes transfered in " + (t / 1000) + " seconds at " + ((tot / 1000) / Math.max(1, (t / 1000))) + "Kbytes/sec");

        in.close();
        out.close();
    }

    public static List<String> listFiles(String[] argv) throws Exception {
        List<String> fileList = new ArrayList<>();
//        List<String> dirList = new ArrayList<>();
        for (int a = 0; a < argv.length; a++) {
            SmbFile file;
            SmbFile[] files = new SmbFile[0];

            file = new SmbFile(argv[a]);

            long t1 = System.currentTimeMillis();
            try {
                files = file.listFiles();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long t2 = System.currentTimeMillis() - t1;

            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains("/")) {
                    //we no like directorys
                    System.out.print(" " + files[i].getName() + "- is dir\n");
//                    dirList.add(files[i].getName());
                } else {
                    System.out.print(" " + files[i].getName() + "\n");
                    fileList.add(files[i].getName());
                }

            }
            System.out.println();
            System.out.println(files.length + " files in " + t2 + "ms");

        }
        return fileList;
    }

    public static List<String> listDirs(String[] argv) throws Exception {
//        List<String> fileList = new ArrayList<>();
        List<String> dirList = new ArrayList<>();
        for (int a = 0; a < argv.length; a++) {
            SmbFile file;
            SmbFile[] files = new SmbFile[0];

            file = new SmbFile(argv[a]);

            long t1 = System.currentTimeMillis();
            try {
                files = file.listFiles();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long t2 = System.currentTimeMillis() - t1;

            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains("/")) {
                    //we no like directorys
                    System.out.print(" " + files[i].getName() + "- is dir\n");
                    dirList.add(files[i].getName());
                } else {
                    System.out.print(" " + files[i].getName() + "\n");
//                    fileList.add(files[i].getName());
                }

            }
            System.out.println();
            System.out.println(files.length + " files in " + t2 + "ms");

        }
        return dirList;
    }

}
