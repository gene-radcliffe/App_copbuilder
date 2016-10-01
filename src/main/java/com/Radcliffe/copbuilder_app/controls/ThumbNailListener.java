package com.Radcliffe.copbuilder_app.controls;
import java.util.EventListener;
public interface ThumbNailListener extends EventListener {
	void ThumbNailListener(ThumbNailViewEvent tnve);
	void ThumbNailLoadListener(ThumbNailLoadEvent tnle);
}
