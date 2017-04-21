package trigues.com.trueke.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.trigues.entity.Payment;
import com.trigues.entity.Shipment;
import com.trigues.entity.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import trigues.com.trueke.R;
import trigues.com.trueke.dependencyinjection.App;
import trigues.com.trueke.dependencyinjection.activity.ActivityModule;
import trigues.com.trueke.dependencyinjection.view.ViewModule;
import trigues.com.trueke.presenter.UserInfoPresenter;
import trigues.com.trueke.view.UserProfileActivity;

/**
 * Created by mbaque on 09/04/2017.
 */

public class UserProfileActivityImpl extends MenuActivityImpl implements UserProfileActivity {

    @Inject
    UserInfoPresenter presenter;

    @BindView(R.id.user_profile_avatar)
    ImageView userAvatar;

    @BindView(R.id.user_profile_ratingbar)
    RatingBar userRating;

    @BindView(R.id.user_profile_name)
    TextView userName;

    @BindView(R.id.user_profile_email)
    TextView userEmail;

    @BindView(R.id.user_profile_birth_date)
    TextView userBirthDate;

    @BindView(R.id.user_profile_num_products)
    TextView userNumProducts;

    @BindView(R.id.user_profile_num_truekes)
    TextView userNumTruekes;

    @BindView(R.id.user_profile_valorations)
    TextView userNumValorations;

    @BindView(R.id.user_profile_my_addresses)
    View userAdresses;

    @BindView(R.id.user_profile_my_credit_cards)
    View userCreditCards;

    @BindView(R.id.user_profile_change_password)
    View userChangePassword;

    @BindView(R.id.user_profile_change_email)
    View userChangeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
        ButterKnife.bind(this);
       // presenter.showProfile();
        //presenter.showPayments();
         //presenter.showShipments();
        newPayment();
        //changeUserProfile();
    }

    private void changeUserProfile() {
        User user = new User("albert@elputu.com","123456");
      // phone,user,password,email,birthDate
        presenter.changeProfile(user);
    }

    private void newPayment(){
        presenter.newPayment(new Payment(-1,1,"Visa/4B/Euro6000","123456789"
                ,"1990-05-06","Sancho Panza","España","Barcelona","Barcelona",
                8029,"Carrer Diagonal","654654654"));
    }
    private void changePayment(){
        presenter.changePayment(new Payment(2,1,"Visa/4B/Euro6000","123456789"
                ,"1990-05-06","Sancho Panza","España","Barcelona","Barcelona",
                8029,"Carrer Diagonal","654654654"));
        //country,province,city,postalCode,address,name,idCard,phone
    }
    private void deletePayment(){}
    private void newShipment(){}
    private void changeShipment(){
        //changeShipment();
    }
    private void deleteShipment(){}
    @Override
    public void onProfileRetrieved(User user) {
        userName.setText(user.getUser()); //em guardo els valors del user?
        userEmail.setText(user.getEmail());
        userNumProducts.setText(String.valueOf(user.getProducts()));
        userNumTruekes.setText(String.valueOf(user.getTruekes()));
        userRating.setRating(user.getRating());
    }

    @Override
    public void onPaymentRetrieved(List<Payment> returnParam) {
        Toast.makeText(getApplicationContext(),"Payment number1: "+returnParam.get(0).getNumber()+ "\n" +
                "Payment number2: "+returnParam.get(1).getNumber(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShipmentRetrieved(List<Shipment> returnParam) {
        Toast.makeText(getApplicationContext(),"Shipment phone1: "+returnParam.get(0).getPhone()+ "\n" +
                "Shipment phone2: "+returnParam.get(1).getPhone(),Toast.LENGTH_LONG).show();
    }



    @Override
    public void onChangeProfileRetrieved(Boolean returnParam) {
        if(!returnParam)
            Toast.makeText(getApplicationContext(),"El perfil se ha actualizado correctamente",Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnUserDeleted(Boolean returnParam) {
        if(!returnParam)
            Toast.makeText(getApplicationContext(),"Tu cuenta se ha borrado correctamente :(",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNewPaymentCreated(Boolean returnParam) {
        if(!returnParam)
            Toast.makeText(getApplicationContext(),"El nuevo método de pago se ha creado correctamente",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChangePaymentRetrieved(Boolean returnParam) {
        if(!returnParam)
            Toast.makeText(getApplicationContext(),"El método de pago se ha actualizado correctamente",Toast.LENGTH_LONG).show();
    }
}
