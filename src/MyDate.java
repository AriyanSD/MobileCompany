import java.io.Serializable;

public class MyDate implements Cloneable, Comparable<MyDate>, Serializable {
    protected int year;
    protected int month;
    protected int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void print() {
        System.out.println(year + "/" + month + "/" + day);
    }

    public String toString() {
        return year + "/" + month + "/" + day;
    }

    public boolean isExpired(MyDate expiryDate) {
        if (this.year < expiryDate.year) {
            return true;
        } else if (this.year == expiryDate.year && this.month < expiryDate.month) {
            return true;
        } else if (this.year == expiryDate.year && this.month == expiryDate.month && this.day < expiryDate.day) {
            return true;
        }
        return false;

    }

    // LAB4
    public MyDate(MyDate date) {
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }

    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    public int compareTo(MyDate other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }

    // Lab6
    public String toDilimatedString() {
        return year + "," + month + "," + day;
    }
    //Lab7

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    

}
