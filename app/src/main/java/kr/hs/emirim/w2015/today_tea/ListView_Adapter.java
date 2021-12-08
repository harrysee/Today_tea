package kr.hs.emirim.w2015.today_tea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ListView_Adapter extends BaseAdapter {
    // 보여줄 Item 목록을 저장할 List
    ArrayList<ListDataClass> items = null;
    Context context;

    // Adapter 생성자 함수
    public ListView_Adapter(Context context, ArrayList<ListDataClass> items) {
        this.items = items;
        this.context = context;
    }

    public void update(ArrayList<ListDataClass> items){
        this.items = items;
        this.notifyDataSetChanged();
    }
    // Adapter.getCount(), 아이템 개수 반환 함수
    @Override
    public int getCount() {
        return items.size();
    }

    // Adapter.getItem(int position), 해당 위치 아이템 반환 함수
    @Override
    public ListDataClass getItem(int position) {
        return items.get(position);
    }

    // Adapter.getItemId(int position), 해당 위치 반환 함수
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Adapter.getView() 해당위치 뷰 반환 함수
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infalter 구현 방법 1
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);

        // ListView의 Item을 구성하는 뷰 연결
        TextView title = view.findViewById(R.id.review_name);
        TextView ex = view.findViewById(R.id.review_text);

        // ListView의 Item을 구성하는 뷰 세팅
        ListDataClass item = items.get(position);
        title.setText(item.getTitle());		// 해당위치 +1 설정, 배열순으로 0부터 시작
        ex.setText(item.getText());					// item 객체 내용을 가져와 세팅

        // 설정한 view를 반환해줘야 함
        return view;
    }
}
