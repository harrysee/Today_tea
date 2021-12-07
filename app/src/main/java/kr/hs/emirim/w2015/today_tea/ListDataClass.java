package kr.hs.emirim.w2015.today_tea;

public class ListDataClass {
    // 아이템 각각 내용, 'Title'
    private String title;
    // 아이템 각각 이미지 리소스 ID, 'Image'
    private String text;

    // 생성자 함수
    public ListDataClass(String title, String teaname) {
        this.title = title;
        this.text = teaname;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
