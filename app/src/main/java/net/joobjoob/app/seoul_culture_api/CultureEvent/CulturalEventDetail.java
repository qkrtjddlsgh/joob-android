//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package net.joobjoob.app.seoul_culture_api.CultureEvent;
import net.joobjoob.app.seoul_culture_api.Common.BaseActivity;
import net.joobjoob.app.seoul_culture_api.Common.CulturalInfo;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import net.joobjoob.app.seoul_culture_api.R;
import net.joobjoob.app.seoul_culture_api.R.id;
import net.joobjoob.app.seoul_culture_api.R.layout;


//comment : CulturalEventDatail

public class CulturalEventDetail extends BaseActivity {
    private CulturalInfo culturalInfo;
    private ImageView mainImg;
    private TextView culturalTitle;
    private TextView culturalCodeName;
    private TextView culturalDate;
    private TextView culturalTime;
    private TextView culturalPlace;
    private TextView culturalUseTrgt;
    private TextView culturalUseFee;
    private TextView culturalSponsor;
    private TextView culturalInquiry;

    public CulturalEventDetail() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.cultural_event_detail);
        //comment : init call
        this.initView();
        if(this.getIntent() != null && this.getIntent().getSerializableExtra("CulturalInfo") != null) {
            this.culturalInfo = (CulturalInfo)this.getIntent().getSerializableExtra("CulturalInfo");
            this.setData();
        }

    }
    // comment : culturalInfo data set
    private void setData() {
        String url = "";
        String[] mainImgUrl = this.culturalInfo.getMAIN_IMG().split("\\/");

        for(int i = 0; i < mainImgUrl.length; ++i) {
            if(i != 0 && i != 1 && i != 2) {
                if(i == mainImgUrl.length - 1) {
                    url = url + mainImgUrl[i];
                } else {
                    url = url + mainImgUrl[i] + "/";
                }
            } else {
                url = url + mainImgUrl[i].toLowerCase() + "/";
            }
        }
        Glide.with(this).load(url).into(this.mainImg);

        // change : error -> "//"
//         Glide.with(this).load(url).error(R.drawable.bg_bigimg).into(this.mainImg);
        this.culturalTitle.setText(this.culturalInfo.getTITLE());
        this.culturalCodeName.setText(this.culturalInfo.getCODENAME());
        this.culturalDate.setText(this.culturalInfo.getSTRTDATE() + " ~ " + this.culturalInfo.getEND_DATE());
        this.culturalTime.setText(this.culturalInfo.getTIME());
        this.culturalPlace.setText(this.culturalInfo.getPLACE());
        this.culturalUseTrgt.setText(this.culturalInfo.getUSE_TRGT());
        if(!this.culturalInfo.getIS_FREE().equals("") && this.culturalInfo.getIS_FREE().equals("1")) {
            this.culturalUseFee.setText("무료");
        } else {
            this.culturalUseFee.setText(this.culturalInfo.getUSE_FEE());
        }

        this.culturalSponsor.setText(this.culturalInfo.getSPONSOR());
        this.culturalInquiry.setText(this.culturalInfo.getINQUIRY());
    }
    //comment : initView
    private void initView() {
        this.mainImg = (ImageView)this.findViewById(id.main_img);
        this.culturalTitle = (TextView)this.findViewById(id.cultural_title);
        this.culturalCodeName = (TextView)this.findViewById(id.cultural_code_name);
        this.culturalDate = (TextView)this.findViewById(id.cultural_date);
        this.culturalTime = (TextView)this.findViewById(id.cultural_time);
        this.culturalPlace = (TextView)this.findViewById(id.cultural_place);
        this.culturalUseTrgt = (TextView)this.findViewById(id.cultural_use_trgt);
        this.culturalUseFee = (TextView)this.findViewById(id.cultural_use_fee);
        this.culturalSponsor = (TextView)this.findViewById(id.cultural_sponsor);
        this.culturalInquiry = (TextView)this.findViewById(id.cultural_inquiry);
    }

    // comment : Intent call of url
    public void showDetailBRS(View view) {
        if(this.culturalInfo != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri uri = Uri.parse(this.culturalInfo.getORG_LINK());
            intent.setData(uri);
            this.startActivity(intent);
        }

    }
}
