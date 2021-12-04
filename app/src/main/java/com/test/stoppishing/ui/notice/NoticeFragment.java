package com.test.stoppishing.ui.notice;

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

import com.test.stoppishing.databinding.FragmentNoticeBinding;

public class NoticeFragment extends Fragment {

    private NoticeViewModel noticeViewModel;
    private FragmentNoticeBinding binding;
    private String notice_board_url = "https://www.naver.com";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticeViewModel =
                new ViewModelProvider(this).get(NoticeViewModel.class);

        binding = FragmentNoticeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView noticeBoard = binding.noticeBoard;
        noticeBoard.loadUrl(notice_board_url);

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