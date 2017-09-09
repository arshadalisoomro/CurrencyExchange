package org.soomro.android.cuco.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.soomro.android.cuco.R;

/**
 * Created by Arshay on 3/9/2017.
 */
public class AboutActivity extends MaterialAboutActivity {
    @Override
    protected MaterialAboutList getMaterialAboutList(Context context) {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(context.getResources().getString(R.string.app_name))
                .icon(R.mipmap.ic_launcher)
                .build());

        try {

            appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(AboutActivity.this,
                    new IconicsDrawable(AboutActivity.this)
                            .icon(GoogleMaterial.Icon.gmd_info_outline)
                            .color(ContextCompat.getColor(AboutActivity.this, android.R.color.black))
                            .sizeDp(18),
                    "Version",
                    false));
            /*appCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(AboutActivity.this,
                    new IconicsDrawable(AboutActivity.this)
                            .icon(CommunityMaterial.Icon.cmd_star)
                            .color(ContextCompat.getColor(AboutActivity.this, R.color.green))
                            .sizeDp(18),
                    "Rate this app",
                    null
            ));*/

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("Author");

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Arshad Ali Soomro")
                .subText("Sindh Pakistan")
                .icon(new IconicsDrawable(AboutActivity.this)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(AboutActivity.this, android.R.color.black))
                        .sizeDp(18))
                /*.setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        startActivity(new Intent(AboutActivity.this, AboutMeActivity.class));
                    }
                })*/
                .build());

        authorCardBuilder.addItem(ConvenienceBuilder.createMapItem(AboutActivity.this,
                new IconicsDrawable(AboutActivity.this)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .color(ContextCompat.getColor(AboutActivity.this, android.R.color.black))
                        .sizeDp(18),
                "Visit Gambat, Sindh",
                null,
                "Gambat, Sindh"));

        authorCardBuilder.addItem(ConvenienceBuilder.createEmailItem(AboutActivity.this,
                new IconicsDrawable(AboutActivity.this)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(AboutActivity.this, android.R.color.black))
                        .sizeDp(18),
                "Send an email",
                true,
                "arshadalisoomro7@gmail.com",
                "Question concerning Connect In"));

        authorCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(AboutActivity.this,
                new IconicsDrawable(AboutActivity.this)
                        .icon(CommunityMaterial.Icon.cmd_phone)
                        .color(ContextCompat.getColor(AboutActivity.this, android.R.color.black))
                        .sizeDp(18),
                "Call me",
                true,
                "+92 305 3369683"));

        return new MaterialAboutList(appCardBuilder.build(),
                authorCardBuilder.build());
    }

    @Override
    protected CharSequence getActivityTitle() {
        return null;
    }
}
