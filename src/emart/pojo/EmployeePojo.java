
package emart.pojo;


public class EmployeePojo {
    private String empid;
    private String empname;
    private String job;
    private double salary;

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmpid() {
        return empid;
    }

    public String getEmpname() {
        return empname;
    }

    public String getJob() {
        return job;
    }

    public double getSalary() {
        return salary;
    }
}
