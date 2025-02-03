package com.example.read.view.activity.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.repository.model.Essay;
import com.example.read.repository.network.GetEssay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.jsonutils.JSONGetProse;
import com.example.read.utils.saveutils.SaveGetEssay;

import java.net.MalformedURLException;

public class ProseActivity extends AppCompatActivity {

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
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView() {
        mTvProseTitle = findViewById(R.id.tv_proseTitle);
        mTvProseAuthor = findViewById(R.id.tv_proseAuthor);
        mTvProseContent = findViewById(R.id.tv_proseContent);
    }

    private void initEvent() throws MalformedURLException {
        showProse();
    }

    private void showProse() throws MalformedURLException {
        GetEssay.getAProse(this);
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