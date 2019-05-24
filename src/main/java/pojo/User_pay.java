package pojo;

public class User_pay {
    private int id,star;
    private String phone,orginalImage,inputInfo,resultImage1,resultImage2,pay_time,evaluate,evaluate_time;
    private float money;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrginalImage() {
        return orginalImage;
    }

    public void setOrginalImage(String orginalImage) {
        this.orginalImage = orginalImage;
    }

    public String getInputInfo() {
        return inputInfo;
    }

    public void setInputInfo(String inputInfo) {
        this.inputInfo = inputInfo;
    }

    public String getResultImage1() {
        return resultImage1;
    }

    public void setResultImage1(String resultImage1) {
        this.resultImage1 = resultImage1;
    }

    public String getResultImage2() {
        return resultImage2;
    }

    public void setResultImage2(String resultImage2) {
        this.resultImage2 = resultImage2;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(String evaluate_time) {
        this.evaluate_time = evaluate_time;
    }

    @Override
    public String toString() {
        return "User_pay{" +
                "id=" + id +
                ", star=" + star +
                ", phone='" + phone + '\'' +
                ", orginalImage='" + orginalImage + '\'' +
                ", inputInfo='" + inputInfo + '\'' +
                ", resultImage1='" + resultImage1 + '\'' +
                ", resultImage2='" + resultImage2 + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", evaluate_time='" + evaluate_time + '\'' +
                ", money=" + money +
                '}';
    }
}
