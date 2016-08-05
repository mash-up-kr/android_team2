package kr.mashup.team2.promise.Map;

import java.util.List;

public interface OnFinishSearchListener {
	public void onSuccess(List<SearchItem> itemList);
	public void onFail();
}
