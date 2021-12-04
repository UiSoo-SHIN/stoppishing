package com.test.stoppishing.ui.qna;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.test.stoppishing.databinding.FragmentQnaBinding;

public class QnAFragment extends Fragment {

    private QnAViewModel qnAViewModel;
    private FragmentQnaBinding binding;
    private String qna_board_url = "https://www.naver.com";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qnAViewModel =
                new ViewModelProvider(this).get(QnAViewModel.class);

        binding = FragmentQnaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final WebView noticeBoard = binding.noticeBoard;
        noticeBoard.loadUrl(qna_board_url);

        //현재 앱을 나가서 새로운 브라우저를 열지 않도록함.
        noticeBoard.setWebViewClient(new WebViewClient());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}