/**
* description ： TODO:ProseActivity 是一个用于阅读散文的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，14:22
*/
package com.example.read.view.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.repository.model.Essay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.jsonutils.JSONGetProse;
import com.example.read.utils.views.BackView;

public class ProseActivity extends AppCompatActivity {

    private BackView mBvProse;
    private TextView mTvProseTitle;
    private TextView mTvProseAuthor;
    private TextView mTvProseContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prose);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    /**
     * 初始化视图组件，绑定 UI 元素。
     */
    private void initView() {
        mBvProse = findViewById(R.id.bv_prose);
        mTvProseTitle = findViewById(R.id.tv_proseTitle);
        mTvProseAuthor = findViewById(R.id.tv_proseAuthor);
        mTvProseContent = findViewById(R.id.tv_proseContent);
    }

    /**
     * 初始化事件监听器，设置返回按钮的点击事件。
     */
    private void initEvent() {
        mBvProse.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showProse();
    }

    /**
     * 加载并展示散文内容，解析 JSON 数据并显示在 TextView 中。
     */
    private void showProse() {
        Essay essay = Essay.PROSE;
        String getEssay = essay.get(this);
        JSONGetProse jsonGetProse = JSONGetProse.parseJSON(getEssay);

        if (jsonGetProse != null && jsonGetProse.getData() != null) {
            JSONGetProse.GetProseData prose = jsonGetProse.getData();
            mTvProseTitle.setText(prose.getTitle());
            mTvProseAuthor.setText(prose.getAuthor());
            String content = prose.getContent();
            content = content.replaceAll("<div[^>]*>.*?</div>", "");
            content = content.replaceAll("&nbsp;", " ");
            content = content.replaceAll("</p><p>", "\n");
            content = content.replaceAll("<[^>]*>", "");
            mTvProseContent.setText(content);
        } else {
            Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
            IntentUtils.intent(this, HomeActivity.class);
        }
    }
}