package de.telekom.sea.mystuff.frontend.android.util;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.MyStuffContext;
import lombok.Getter;

public class MyStuffViewModel extends ViewModel {

    @Getter
    private MyStuffContext myContext;

    public MyStuffViewModel() {
        super();
    }

    public void initContext(MyStuffContext ctx) {
        this.myContext = ctx;
    }

    public void initWithApplication(Application application) {
        initContext(((MyStuffApplication)application).getStuffContext());
    }

}
