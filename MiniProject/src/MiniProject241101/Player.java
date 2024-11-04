package MiniProject241101;

//Player.java
public class Player {
 private String name;
 private String nickname;
 private int age;
 private String team;

 public Player(String name, String nickname, int age, String team) {
     this.name = name;
     this.nickname = nickname;
     this.age = age;
     this.team = team;
 }

 // Getters and toString method
 public String getName() {
     return name;
 }

 public String getNickname() {
     return nickname;
 }

 public int getAge() {
     return age;
 }

 public String getTeam() {
     return team;
 }

 @Override
 public String toString() {
     return "Name: " + name + ", Nickname: " + nickname + ", Age: " + age + ", Team: " + team;
 }
}
