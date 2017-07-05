package androidpermission.com.bw.test.dliao.base;

/**
 * Created by Administrator on 2017/7/4.
 */

public abstract  class BasePresenter<T> {

    public T view;

    public void attach(T view){
        this.view=view;
    }

    public void detach(){

        this.view=null;
    }


}
