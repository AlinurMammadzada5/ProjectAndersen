package com.app.Database;

import jakarta.persistence.*;

@Entity
@Table(name = "Spaces")
public class Spaces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TableID")
    private int id;

    @Column(name = "Tablebool")
    private boolean reserved;

    @Column(name = "Username")
    private String username;

    public int getId() { return id; }

    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "id=" + id +
                ", reserved=" + reserved +
                ", username='" + username + '\'';
    }
}
