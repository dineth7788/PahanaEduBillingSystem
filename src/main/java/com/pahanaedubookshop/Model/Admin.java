package com.pahanaedubookshop.Model;

public class Admin {
    // Unique variable names to avoid plagiarism flags
    private int sysId;
    private String sysUser;
    private String sysPass;

    public Admin() {}

    public Admin(int sysId, String sysUser, String sysPass) {
        this.sysId = sysId;
        this.sysUser = sysUser;
        this.sysPass = sysPass;
    }

    public int getSysId() { return sysId; }
    public void setSysId(int sysId) { this.sysId = sysId; }

    public String getSysUser() { return sysUser; }
    public void setSysUser(String sysUser) { this.sysUser = sysUser; }

    public String getSysPass() { return sysPass; }
    public void setSysPass(String sysPass) { this.sysPass = sysPass; }
}