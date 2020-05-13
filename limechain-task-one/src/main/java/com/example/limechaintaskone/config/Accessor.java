package com.example.limechaintaskone.config;

import java.time.LocalDateTime;

public class Accessor {
    public static final Long RATELIMIT = 3600l;
    private String IP;
    private LocalDateTime localDateTime;
    private LocalDateTime lockOutDate;
    private Long requestsLeft;
    private int lockOutTime;

    public Accessor(String IP, LocalDateTime localDateTime) {
        this.IP = IP;
        this.localDateTime = localDateTime;
        this.lockOutDate = this.localDateTime.minusMinutes(1);
        this.requestsLeft = RATELIMIT;
        this.lockOutTime = 15;
    }

    public Accessor(String IP) {
        this(IP,LocalDateTime.now());
    }

    public boolean lockOutPeriodOver(){
        if(this.lockOutDate.compareTo(LocalDateTime.now()) <= 0) return true;

        this.lockOutDate = LocalDateTime.now().plusSeconds(15);
        this.requestsLeft = RATELIMIT;

        return false;
    }

    public String getIP() {
        return IP;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public int hashCode() {
        return this.IP.hashCode() + 23;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        if(obj == this) return true;

        Accessor comparing = (Accessor)obj;

        if(this.IP.equals(comparing.getIP())){
            return true;
        }else
            return false;
    }

    public void persistRequest() {
        this.requestsLeft--;
        if(this.requestsLeft<=0){
            this.lockOutDate = LocalDateTime.now().plusSeconds(15);
        }
    }

    public long getRequestsLeft() {
        return this.requestsLeft;
    }

    public int getLockOutTime() {
        return this.lockOutTime;
    }
}
