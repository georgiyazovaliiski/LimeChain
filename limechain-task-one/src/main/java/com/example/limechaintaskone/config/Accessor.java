package com.example.limechaintaskone.config;

import java.time.LocalDateTime;

public class Accessor {
    public static final Long RATELIMIT = 3600l;
    public static final Long LOCKOUTTIME = 15l;
    private String IP;
    private LocalDateTime firstRequestTime;
    private LocalDateTime lockOutDate;
    private Long requestsLeft;

    public Accessor(String IP, LocalDateTime firstRequestTime) {
        this.IP = IP;
        this.firstRequestTime = firstRequestTime;
        this.lockOutDate = this.firstRequestTime.minusMinutes(1);
        this.requestsLeft = RATELIMIT;
    }

    public Accessor(String IP) {
        this(IP,LocalDateTime.now());
    }

    public boolean lockOutPeriodOver(){
        if(this.lockOutDate.compareTo(LocalDateTime.now()) <= 0 && this.requestsLeft == RATELIMIT){
            this.firstRequestTime = LocalDateTime.now();
            return true;
        }else if(this.lockOutDate.compareTo(LocalDateTime.now()) <= 0){
            return true;
        }

        this.lockOutDate = LocalDateTime.now().plusMinutes(this.LOCKOUTTIME);
        this.requestsLeft = RATELIMIT;

        return false;
    }

    public String getIP() {
        return IP;
    }

    public LocalDateTime getFirstRequestTime() {
        return firstRequestTime;
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

        if(this.requestsLeft<=0 && LocalDateTime.now().minusMinutes(60).compareTo(this.firstRequestTime)<=0){
            this.lockOutDate = LocalDateTime.now().plusSeconds(this.LOCKOUTTIME);
        }else if(LocalDateTime.now().minusMinutes(60).compareTo(this.firstRequestTime)>0){
            this.requestsLeft = RATELIMIT;
        }
    }

    public long getRequestsLeft() {
        return this.requestsLeft;
    }
}
