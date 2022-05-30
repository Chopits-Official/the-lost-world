package com.chopits.tlw.entity.player;

public interface IPlayerEntity {
    float getMaxMagic();
    float getMagic();
    void setMagic(float magic);
    int getMaxTalentLevel();
    int getTalentLevel();
    void setTalentLevel(int talentLevel);
    int getMaxStr();
    int getStr();
    void setStr(int str);
    int getMaxMys();
    int getMys();
    void setMys(int mys);
    int getMaxDex();
    int getDex();
    void setDex(int dex);
    int getMaxCon();
    int getCon();
    void setCon(int con);
    int getMaxBel();
    int getBel();
    void setBel(int bel);
    boolean talentAccessible(int i);
}
