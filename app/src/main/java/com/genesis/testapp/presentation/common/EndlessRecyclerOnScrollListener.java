package com.genesis.testapp.presentation.common;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private static final int DEFAULT_VISIBLE_THRESHOLD = 5;
    private static final int DEFAULT_SKIP_THRESHOLD = 1;

    private int previousTotal = 0;
    private boolean loading = true;
    private final int visibleThreshold;
    private final int skipThreshold;

    private int currentPage = 1;

    private LinearLayoutManager layoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this(linearLayoutManager, DEFAULT_VISIBLE_THRESHOLD, DEFAULT_SKIP_THRESHOLD);
    }

    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager,
            int visibleThreshold,
            int skipThreshold
    ) {
        this.layoutManager = linearLayoutManager;
        this.visibleThreshold = visibleThreshold;
        this.skipThreshold = skipThreshold;
    }

    @Override
    public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = layoutManager.getItemCount();
        final int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (totalItemCount <= skipThreshold) {
            return;
        }

        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            currentPage++;

            onLoadMore(currentPage);

            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
