package kr.hs.emirim.w2015.today_tea;

public class TeaDataClass {
    String teaName;
    String teaEfficacy;
    String teaExplan;
    int teaImg;
    int mindkey;

    public TeaDataClass(){}

    public TeaDataClass(String teaName,String teaEfficacy, String teaExplan,int teaImg, int mindkey){
        this.teaName = teaName;
        this.teaEfficacy = teaEfficacy;
        this.teaExplan = teaExplan;
        this.teaImg = teaImg;
        this.mindkey = mindkey;
    }
}
