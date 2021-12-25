package fr.loris.gottagras.uhc.infos;

public enum timers {
    PVE(30),
    PVP(20 * 60),
    BORDER(60 * 60),
    MEETUP(80 * 60);

    private int time;

    timers(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
