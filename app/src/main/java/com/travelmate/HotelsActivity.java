package com.travelmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.travelmate.data.Hotels;
import com.travelmate.services.HotelsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Betim on 6/10/2018.
 */

public class HotelsActivity extends Activity {
    GridView gvHotels;
    HotelsAdapter adapter;
    ArrayList<Hotels> hotels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        gvHotels = (GridView)findViewById(R.id.gv);
        hotels = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String url = "https://fake-hotel-api.herokuapp.com/api/hotels";
/*
        try {
            JSONArray body = new JSONArray("[\n" +
                    "{\n" +
                    "\"id\": \"93a8c9b4-8349-44a4-8631-ee996361080a\",\n" +
                    "\"name\": \"culpa nihil ut\",\n" +
                    "\"country\": \"American Samoa\",\n" +
                    "\"city\": \"Charityshire\",\n" +
                    "\"price\": 154,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?58606\",\n" +
                    "\"http://lorempixel.com/640/480/city?76550\",\n" +
                    "\"http://lorempixel.com/640/480/city?23643\",\n" +
                    "\"http://lorempixel.com/640/480/city?5157\",\n" +
                    "\"http://lorempixel.com/640/480/city?91083\",\n" +
                    "\"http://lorempixel.com/640/480/city?11995\",\n" +
                    "\"http://lorempixel.com/640/480/city?39843\",\n" +
                    "\"http://lorempixel.com/640/480/city?79277\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-31T23:13:54.230Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 4.6888917688467515,\n" +
                    "\"description\": \"Soluta in neque est vel. Quibusdam delectus unde voluptatem et autem ullam sed. Aut aut eum sint iste cupiditate est iste quis sit. Voluptatum natus beatae et ex quis nesciunt vel accusamus omnis. Nam inventore sed et. Harum quis qui voluptatem qui minima id minus.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ecb8094b-60e9-4929-9386-a4461e12ff62\",\n" +
                    "\"name\": \"et quaerat cupiditate\",\n" +
                    "\"country\": \"Senegal\",\n" +
                    "\"city\": \"Danykahaven\",\n" +
                    "\"price\": 491,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?58750\",\n" +
                    "\"http://lorempixel.com/640/480/city?11834\",\n" +
                    "\"http://lorempixel.com/640/480/city?38895\",\n" +
                    "\"http://lorempixel.com/640/480/city?96750\",\n" +
                    "\"http://lorempixel.com/640/480/city?28296\",\n" +
                    "\"http://lorempixel.com/640/480/city?54406\",\n" +
                    "\"http://lorempixel.com/640/480/city?13457\",\n" +
                    "\"http://lorempixel.com/640/480/city?78193\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-05-17T08:56:25.915Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 2.8504294743652117,\n" +
                    "\"description\": \"Excepturi mollitia accusantium. Maxime ut sunt voluptatem. Perferendis voluptates esse ad eos. Dolor molestiae nostrum hic unde.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"1d985cd4-e4a4-41d7-b4a4-382050ebf602\",\n" +
                    "\"name\": \"magnam blanditiis reiciendis\",\n" +
                    "\"country\": \"Guam\",\n" +
                    "\"city\": \"West Anais\",\n" +
                    "\"price\": 841,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?23044\",\n" +
                    "\"http://lorempixel.com/640/480/city?53958\",\n" +
                    "\"http://lorempixel.com/640/480/city?28518\",\n" +
                    "\"http://lorempixel.com/640/480/city?75562\",\n" +
                    "\"http://lorempixel.com/640/480/city?95060\",\n" +
                    "\"http://lorempixel.com/640/480/city?82137\",\n" +
                    "\"http://lorempixel.com/640/480/city?13780\",\n" +
                    "\"http://lorempixel.com/640/480/city?73059\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-01-30T09:29:00.856Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 1.1660509536262045,\n" +
                    "\"description\": \"Quia corrupti quia et. Autem blanditiis qui illum quos vero animi. Alias perspiciatis rerum itaque eius assumenda.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"0f90d150-39cb-419a-91d4-806ea984ef61\",\n" +
                    "\"name\": \"voluptate voluptas omnis\",\n" +
                    "\"country\": \"Cape Verde\",\n" +
                    "\"city\": \"Welchhaven\",\n" +
                    "\"price\": 510,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?85372\",\n" +
                    "\"http://lorempixel.com/640/480/city?62483\",\n" +
                    "\"http://lorempixel.com/640/480/city?48102\",\n" +
                    "\"http://lorempixel.com/640/480/city?83941\",\n" +
                    "\"http://lorempixel.com/640/480/city?10402\",\n" +
                    "\"http://lorempixel.com/640/480/city?35018\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-09-21T16:40:42.483Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 2.985365284910925,\n" +
                    "\"description\": \"Voluptatum eligendi enim. Blanditiis quis quo reprehenderit facere. Distinctio distinctio vero. Consequatur recusandae quae.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d9231e88-783b-4a43-8eb3-ae15e39d9771\",\n" +
                    "\"name\": \"rerum repellendus consequatur\",\n" +
                    "\"country\": \"Argentina\",\n" +
                    "\"city\": \"Kundebury\",\n" +
                    "\"price\": 268,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?69732\",\n" +
                    "\"http://lorempixel.com/640/480/city?30504\",\n" +
                    "\"http://lorempixel.com/640/480/city?73700\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-01-08T21:37:16.826Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 5.517813855121661,\n" +
                    "\"description\": \"Quo ab odit ut repellendus. Totam ipsa vitae dicta itaque quo neque ut possimus et. Praesentium voluptatibus provident quisquam earum quia totam rem. Cupiditate culpa nemo voluptatem rerum et dignissimos voluptate. Aperiam aut soluta et non ea. Quo maiores dolore vero at illum vero dolorem voluptatem provident.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"3d959247-708f-4198-882c-1b29d3867368\",\n" +
                    "\"name\": \"nam aut itaque\",\n" +
                    "\"country\": \"Paraguay\",\n" +
                    "\"city\": \"Lake Casper\",\n" +
                    "\"price\": 215,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?36524\",\n" +
                    "\"http://lorempixel.com/640/480/city?478\",\n" +
                    "\"http://lorempixel.com/640/480/city?15721\",\n" +
                    "\"http://lorempixel.com/640/480/city?11179\",\n" +
                    "\"http://lorempixel.com/640/480/city?39037\",\n" +
                    "\"http://lorempixel.com/640/480/city?65912\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-03T07:04:37.849Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 5.5358850190576785,\n" +
                    "\"description\": \"Eos architecto vero quo odit facilis laboriosam similique. Nemo dicta asperiores atque nihil eligendi ad distinctio blanditiis voluptatem. Voluptatem fuga ipsa laudantium hic. Accusamus et officia numquam. Labore expedita animi velit quasi maiores ipsum. In autem sequi sequi sit omnis.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"1d6d1cea-4050-4b5b-a932-b0561e4f02d1\",\n" +
                    "\"name\": \"beatae quia commodi\",\n" +
                    "\"country\": \"Cook Islands\",\n" +
                    "\"city\": \"Champlinbury\",\n" +
                    "\"price\": 556,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?72900\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-04-03T05:48:13.163Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 1.6583328702645175,\n" +
                    "\"description\": \"Vel magnam aut dolor. Et repellat commodi recusandae temporibus. Dolores iste temporibus adipisci. Aut occaecati perspiciatis reiciendis velit sit labore voluptate suscipit commodi.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"13377bc4-c84e-4593-ae1b-eb479468cb15\",\n" +
                    "\"name\": \"aut minus corrupti\",\n" +
                    "\"country\": \"Eritrea\",\n" +
                    "\"city\": \"New Emma\",\n" +
                    "\"price\": 699,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?49836\",\n" +
                    "\"http://lorempixel.com/640/480/city?26834\",\n" +
                    "\"http://lorempixel.com/640/480/city?35759\",\n" +
                    "\"http://lorempixel.com/640/480/city?65351\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-06-07T00:40:54.428Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 5.005803185625732,\n" +
                    "\"description\": \"Praesentium est ex saepe voluptatem tempore voluptates cupiditate. Corrupti quia tempore qui adipisci dignissimos iste. Totam optio consectetur eos.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"a39ddf50-3f9f-4587-b1c3-cea74447adde\",\n" +
                    "\"name\": \"non consequatur nobis\",\n" +
                    "\"country\": \"Guyana\",\n" +
                    "\"city\": \"Port Billie\",\n" +
                    "\"price\": 644,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?53869\",\n" +
                    "\"http://lorempixel.com/640/480/city?74915\",\n" +
                    "\"http://lorempixel.com/640/480/city?24893\",\n" +
                    "\"http://lorempixel.com/640/480/city?27661\",\n" +
                    "\"http://lorempixel.com/640/480/city?96923\",\n" +
                    "\"http://lorempixel.com/640/480/city?46422\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-07T23:41:01.725Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 4.07841254466089,\n" +
                    "\"description\": \"Fugiat at unde eaque quia eveniet maxime quo nemo. Alias sint nobis rerum officia. Voluptates sed vel et ea optio.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"f3e77314-ee17-4423-a908-90fb87ab2d03\",\n" +
                    "\"name\": \"omnis repellendus sunt\",\n" +
                    "\"country\": \"Antigua and Barbuda\",\n" +
                    "\"city\": \"New Audreymouth\",\n" +
                    "\"price\": 965,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?89810\",\n" +
                    "\"http://lorempixel.com/640/480/city?76279\",\n" +
                    "\"http://lorempixel.com/640/480/city?94795\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-09-16T17:33:48.820Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 4.389068573166593,\n" +
                    "\"description\": \"At sint magni aut numquam officia soluta ullam. Ex necessitatibus provident repellendus eligendi vitae. Animi distinctio molestias. Iusto incidunt at dolorem ipsam. Qui nobis dicta itaque vitae repudiandae. Alias reprehenderit officia sed nemo id sit consequatur.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d03dda97-0811-4427-a362-0370e815ba8d\",\n" +
                    "\"name\": \"beatae totam perferendis\",\n" +
                    "\"country\": \"Honduras\",\n" +
                    "\"city\": \"Matildaville\",\n" +
                    "\"price\": 160,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?84290\",\n" +
                    "\"http://lorempixel.com/640/480/city?28141\",\n" +
                    "\"http://lorempixel.com/640/480/city?74190\",\n" +
                    "\"http://lorempixel.com/640/480/city?5128\",\n" +
                    "\"http://lorempixel.com/640/480/city?42786\",\n" +
                    "\"http://lorempixel.com/640/480/city?94096\",\n" +
                    "\"http://lorempixel.com/640/480/city?34356\",\n" +
                    "\"http://lorempixel.com/640/480/city?54927\",\n" +
                    "\"http://lorempixel.com/640/480/city?57981\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-15T04:34:54.341Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 2.194235269711848,\n" +
                    "\"description\": \"Ut incidunt sint reiciendis quasi officiis. Molestias dolorem quaerat eligendi ex dolor omnis et voluptatem nam. Et ea voluptates suscipit aliquam velit nihil dolor. Eaque fugiat consequuntur incidunt mollitia quo. Sapiente nostrum quia vel consequuntur fugit facere sint labore beatae.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d667cf85-d5d6-4967-b22e-974015700f77\",\n" +
                    "\"name\": \"quod repellat quia\",\n" +
                    "\"country\": \"Palestinian Territory\",\n" +
                    "\"city\": \"East Imelda\",\n" +
                    "\"price\": 975,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?41212\",\n" +
                    "\"http://lorempixel.com/640/480/city?10812\",\n" +
                    "\"http://lorempixel.com/640/480/city?33497\",\n" +
                    "\"http://lorempixel.com/640/480/city?56305\",\n" +
                    "\"http://lorempixel.com/640/480/city?12775\",\n" +
                    "\"http://lorempixel.com/640/480/city?6394\",\n" +
                    "\"http://lorempixel.com/640/480/city?33366\",\n" +
                    "\"http://lorempixel.com/640/480/city?75724\",\n" +
                    "\"http://lorempixel.com/640/480/city?20408\",\n" +
                    "\"http://lorempixel.com/640/480/city?49706\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-01-16T07:17:10.450Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 3.5416072341725986,\n" +
                    "\"description\": \"Facere labore neque. Possimus nisi voluptates sed quasi cum minima vero exercitationem. Molestiae ipsum molestias qui sint neque ut fugit soluta. Ipsum non consequatur incidunt nostrum asperiores. Non quo molestiae perspiciatis quae amet tempore. Est architecto quibusdam incidunt dolorem minus voluptates et.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"7e181e58-3213-487c-8aa5-b46ac278e96b\",\n" +
                    "\"name\": \"sunt ea doloribus\",\n" +
                    "\"country\": \"Mongolia\",\n" +
                    "\"city\": \"Stanburgh\",\n" +
                    "\"price\": 597,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?64643\",\n" +
                    "\"http://lorempixel.com/640/480/city?5902\",\n" +
                    "\"http://lorempixel.com/640/480/city?89023\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-12T01:29:28.779Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 5.4411550757680995,\n" +
                    "\"description\": \"Nam pariatur harum placeat delectus. Reiciendis perferendis quod consequatur autem ea dolorem molestias perferendis repudiandae. Voluptatem atque omnis quae sint necessitatibus alias. Exercitationem sit officia minima id qui tenetur est eligendi et. Laudantium quisquam eius voluptas eum nihil deserunt. Pariatur qui ipsam unde vitae dolores asperiores quia.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"88eb6978-d73e-44aa-8bd5-f7910eb736ec\",\n" +
                    "\"name\": \"eveniet nihil est\",\n" +
                    "\"country\": \"Suriname\",\n" +
                    "\"city\": \"Kayleyton\",\n" +
                    "\"price\": 160,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?95624\",\n" +
                    "\"http://lorempixel.com/640/480/city?80271\",\n" +
                    "\"http://lorempixel.com/640/480/city?18937\",\n" +
                    "\"http://lorempixel.com/640/480/city?22348\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-10T17:01:20.898Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 3.123200738342143,\n" +
                    "\"description\": \"Illum nostrum est et voluptatem ullam velit aut. Nihil eaque incidunt a eos. Reiciendis inventore dolorum est sed.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"a303b606-cc3e-4ad1-8a34-5b9dc9945ea3\",\n" +
                    "\"name\": \"asperiores reiciendis sunt\",\n" +
                    "\"country\": \"Austria\",\n" +
                    "\"city\": \"Kristinburgh\",\n" +
                    "\"price\": 575,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?98170\",\n" +
                    "\"http://lorempixel.com/640/480/city?93462\",\n" +
                    "\"http://lorempixel.com/640/480/city?82637\",\n" +
                    "\"http://lorempixel.com/640/480/city?6472\",\n" +
                    "\"http://lorempixel.com/640/480/city?49601\",\n" +
                    "\"http://lorempixel.com/640/480/city?40616\",\n" +
                    "\"http://lorempixel.com/640/480/city?28590\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-07-13T00:48:12.204Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 0.5174773604832996,\n" +
                    "\"description\": \"Corporis et totam nisi quis eius occaecati non. Et id nesciunt et voluptates enim rem rem. Ipsum repudiandae iusto quia ipsam. Deserunt repudiandae ex iste quo modi et dignissimos nisi a.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ee4fb2ff-76ec-45e2-a68e-ef4bc235b158\",\n" +
                    "\"name\": \"facere pariatur optio\",\n" +
                    "\"country\": \"Virgin Islands, British\",\n" +
                    "\"city\": \"New Eastonstad\",\n" +
                    "\"price\": 256,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?87076\",\n" +
                    "\"http://lorempixel.com/640/480/city?87891\",\n" +
                    "\"http://lorempixel.com/640/480/city?52546\",\n" +
                    "\"http://lorempixel.com/640/480/city?28199\",\n" +
                    "\"http://lorempixel.com/640/480/city?59569\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-22T04:28:07.139Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 2.24739266343023,\n" +
                    "\"description\": \"Quae et et. Amet recusandae dolores accusamus. Repudiandae delectus beatae quia consectetur quia dolorem corporis. Soluta facere repellat ducimus. Illum cumque dolorem.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"a11cb4dd-356c-41f1-8ca6-61ded5ef1843\",\n" +
                    "\"name\": \"non omnis molestiae\",\n" +
                    "\"country\": \"Peru\",\n" +
                    "\"city\": \"Josephinefort\",\n" +
                    "\"price\": 410,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?61526\",\n" +
                    "\"http://lorempixel.com/640/480/city?29818\",\n" +
                    "\"http://lorempixel.com/640/480/city?91175\",\n" +
                    "\"http://lorempixel.com/640/480/city?87706\",\n" +
                    "\"http://lorempixel.com/640/480/city?41179\",\n" +
                    "\"http://lorempixel.com/640/480/city?11246\",\n" +
                    "\"http://lorempixel.com/640/480/city?8946\",\n" +
                    "\"http://lorempixel.com/640/480/city?18836\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-12T11:38:58.587Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 0.561962894899763,\n" +
                    "\"description\": \"Itaque itaque tempore sapiente dolores voluptatem voluptatibus nihil quod dolor. Laudantium ea in aut tempore facere. Rerum fugit similique dolorum.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"77406dfd-bdd2-4524-aebd-893bf0abd471\",\n" +
                    "\"name\": \"voluptas explicabo alias\",\n" +
                    "\"country\": \"New Caledonia\",\n" +
                    "\"city\": \"South Marionbury\",\n" +
                    "\"price\": 883,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?33426\",\n" +
                    "\"http://lorempixel.com/640/480/city?31087\",\n" +
                    "\"http://lorempixel.com/640/480/city?8896\",\n" +
                    "\"http://lorempixel.com/640/480/city?9734\",\n" +
                    "\"http://lorempixel.com/640/480/city?12715\",\n" +
                    "\"http://lorempixel.com/640/480/city?96489\",\n" +
                    "\"http://lorempixel.com/640/480/city?15296\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-05-05T14:46:59.233Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 5.684066034178905,\n" +
                    "\"description\": \"Cumque nobis minima. Officia veritatis expedita dolor dolores minus et vero. Ratione officia quaerat mollitia quod et accusantium nostrum nihil sint. Laboriosam aut delectus et omnis ratione eum aut explicabo.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"aa722635-5412-4ec4-afc4-1db4ba08a08d\",\n" +
                    "\"name\": \"ab dolor reiciendis\",\n" +
                    "\"country\": \"Monaco\",\n" +
                    "\"city\": \"Lake Faustinoport\",\n" +
                    "\"price\": 784,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?4710\",\n" +
                    "\"http://lorempixel.com/640/480/city?92028\",\n" +
                    "\"http://lorempixel.com/640/480/city?91597\",\n" +
                    "\"http://lorempixel.com/640/480/city?30597\",\n" +
                    "\"http://lorempixel.com/640/480/city?91009\",\n" +
                    "\"http://lorempixel.com/640/480/city?78588\",\n" +
                    "\"http://lorempixel.com/640/480/city?17913\",\n" +
                    "\"http://lorempixel.com/640/480/city?69\",\n" +
                    "\"http://lorempixel.com/640/480/city?59354\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-14T11:39:49.463Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 3.631226721607933,\n" +
                    "\"description\": \"Ab tempora omnis quis quia quam. Blanditiis et tempore. Provident ducimus saepe et nesciunt tenetur omnis quibusdam.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"95bc9d68-3d46-4167-886c-424d33adef0a\",\n" +
                    "\"name\": \"dolores nostrum modi\",\n" +
                    "\"country\": \"Puerto Rico\",\n" +
                    "\"city\": \"Ameliatown\",\n" +
                    "\"price\": 432,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?93563\",\n" +
                    "\"http://lorempixel.com/640/480/city?8407\",\n" +
                    "\"http://lorempixel.com/640/480/city?71409\",\n" +
                    "\"http://lorempixel.com/640/480/city?73416\",\n" +
                    "\"http://lorempixel.com/640/480/city?24884\",\n" +
                    "\"http://lorempixel.com/640/480/city?33329\",\n" +
                    "\"http://lorempixel.com/640/480/city?59164\",\n" +
                    "\"http://lorempixel.com/640/480/city?28655\",\n" +
                    "\"http://lorempixel.com/640/480/city?5899\",\n" +
                    "\"http://lorempixel.com/640/480/city?14497\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-02T09:30:19.762Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 5.374541597773081,\n" +
                    "\"description\": \"Dolorem esse iusto. Asperiores ullam magni aliquam qui suscipit veniam aut. Hic dolores quis tempore.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d95e501f-32dd-4a23-887d-68cce14f443a\",\n" +
                    "\"name\": \"neque consectetur voluptatem\",\n" +
                    "\"country\": \"Kyrgyz Republic\",\n" +
                    "\"city\": \"Gerholdfort\",\n" +
                    "\"price\": 827,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?20044\",\n" +
                    "\"http://lorempixel.com/640/480/city?94562\",\n" +
                    "\"http://lorempixel.com/640/480/city?10701\",\n" +
                    "\"http://lorempixel.com/640/480/city?692\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-31T23:00:09.721Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 4.358650019176599,\n" +
                    "\"description\": \"Nesciunt est est qui molestiae consequuntur error aut sequi. Quos et rerum adipisci rerum tempora. Ducimus qui fugiat qui. Officia et doloremque dignissimos ipsam quo quam eligendi illum.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"7d27e781-e23a-4b13-861f-13a7cf343b60\",\n" +
                    "\"name\": \"in incidunt est\",\n" +
                    "\"country\": \"Lao People's Democratic Republic\",\n" +
                    "\"city\": \"South Georgianna\",\n" +
                    "\"price\": 333,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?33773\",\n" +
                    "\"http://lorempixel.com/640/480/city?91052\",\n" +
                    "\"http://lorempixel.com/640/480/city?32423\",\n" +
                    "\"http://lorempixel.com/640/480/city?99407\",\n" +
                    "\"http://lorempixel.com/640/480/city?17607\",\n" +
                    "\"http://lorempixel.com/640/480/city?41777\",\n" +
                    "\"http://lorempixel.com/640/480/city?79167\",\n" +
                    "\"http://lorempixel.com/640/480/city?16577\",\n" +
                    "\"http://lorempixel.com/640/480/city?50355\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-08-17T02:33:31.970Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 0.19416645531104937,\n" +
                    "\"description\": \"Vel ut et hic odio. Nam repudiandae culpa est aliquam. Vel ut quia ab et sequi. Laborum dolorem eos et voluptate. Minima velit enim est explicabo maxime. Beatae doloremque aliquam in odio cumque qui quod.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"da31b4e8-351a-4b9e-8037-ef9d77a86463\",\n" +
                    "\"name\": \"rem recusandae qui\",\n" +
                    "\"country\": \"Comoros\",\n" +
                    "\"city\": \"Muellerville\",\n" +
                    "\"price\": 511,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?14472\",\n" +
                    "\"http://lorempixel.com/640/480/city?75843\",\n" +
                    "\"http://lorempixel.com/640/480/city?76866\",\n" +
                    "\"http://lorempixel.com/640/480/city?50656\",\n" +
                    "\"http://lorempixel.com/640/480/city?64813\",\n" +
                    "\"http://lorempixel.com/640/480/city?19882\",\n" +
                    "\"http://lorempixel.com/640/480/city?63926\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-06-12T00:43:26.225Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 1.8736298261230142,\n" +
                    "\"description\": \"Neque eos nihil velit commodi rerum eaque necessitatibus. Iste aut tenetur quod vel odio non sit. Dolores sequi veritatis omnis debitis aut consectetur. Sunt quisquam omnis. Autem sit quia omnis delectus qui quo.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"19a9081a-9d84-47b7-b544-b8245687c6e6\",\n" +
                    "\"name\": \"sed expedita non\",\n" +
                    "\"country\": \"Kazakhstan\",\n" +
                    "\"city\": \"Schoenberg\",\n" +
                    "\"price\": 988,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?708\",\n" +
                    "\"http://lorempixel.com/640/480/city?27921\",\n" +
                    "\"http://lorempixel.com/640/480/city?61859\",\n" +
                    "\"http://lorempixel.com/640/480/city?53741\",\n" +
                    "\"http://lorempixel.com/640/480/city?17\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-01-21T04:18:15.850Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 1.4881637323707841,\n" +
                    "\"description\": \"Quia qui quis debitis debitis qui porro eius omnis. Et sapiente voluptatem quia amet. Voluptatum aspernatur debitis natus. Mollitia voluptatem inventore sed voluptatem excepturi illum. Adipisci ea laudantium qui sunt omnis.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"40c02e35-fde8-4442-a316-4986aaef8882\",\n" +
                    "\"name\": \"earum eos ullam\",\n" +
                    "\"country\": \"Afghanistan\",\n" +
                    "\"city\": \"South Wiley\",\n" +
                    "\"price\": 620,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?49151\",\n" +
                    "\"http://lorempixel.com/640/480/city?70358\",\n" +
                    "\"http://lorempixel.com/640/480/city?23772\",\n" +
                    "\"http://lorempixel.com/640/480/city?81112\",\n" +
                    "\"http://lorempixel.com/640/480/city?1521\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-25T16:10:08.607Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 4.404249307408921,\n" +
                    "\"description\": \"Sequi dignissimos atque ea eius vero. Dolore qui nihil molestias et aut saepe. Expedita quaerat ducimus iusto. Pariatur odio iusto. Sed rerum eius optio est reprehenderit id ipsam.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"c7a0b2ba-b04f-4a7c-b309-8317e8b2969e\",\n" +
                    "\"name\": \"voluptate molestiae repellendus\",\n" +
                    "\"country\": \"Cayman Islands\",\n" +
                    "\"city\": \"Jadonhaven\",\n" +
                    "\"price\": 502,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?70110\",\n" +
                    "\"http://lorempixel.com/640/480/city?43699\",\n" +
                    "\"http://lorempixel.com/640/480/city?22465\",\n" +
                    "\"http://lorempixel.com/640/480/city?34757\",\n" +
                    "\"http://lorempixel.com/640/480/city?59228\",\n" +
                    "\"http://lorempixel.com/640/480/city?50121\",\n" +
                    "\"http://lorempixel.com/640/480/city?92028\",\n" +
                    "\"http://lorempixel.com/640/480/city?15204\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-04-25T08:03:16.226Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 0.7766295633928801,\n" +
                    "\"description\": \"Similique error quas. Quo numquam voluptas suscipit voluptates. Accusantium doloribus autem.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"a382661d-4e83-4f00-8287-36d44710ef85\",\n" +
                    "\"name\": \"in laudantium voluptates\",\n" +
                    "\"country\": \"Cote d'Ivoire\",\n" +
                    "\"city\": \"Guillermomouth\",\n" +
                    "\"price\": 141,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?6235\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-12T14:54:19.514Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 2.6097650812215423,\n" +
                    "\"description\": \"Qui nihil magnam dolorum fuga ratione. Enim aliquam quia. Rerum nulla exercitationem officiis dolorem ipsa qui. Minus excepturi aut omnis doloribus quam. Accusantium et impedit nisi totam dicta commodi quaerat.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"71bc220e-c38d-435a-b717-23172e94b594\",\n" +
                    "\"name\": \"harum et quam\",\n" +
                    "\"country\": \"Antigua and Barbuda\",\n" +
                    "\"city\": \"Lake Edward\",\n" +
                    "\"price\": 158,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?75256\",\n" +
                    "\"http://lorempixel.com/640/480/city?97937\",\n" +
                    "\"http://lorempixel.com/640/480/city?22772\",\n" +
                    "\"http://lorempixel.com/640/480/city?60795\",\n" +
                    "\"http://lorempixel.com/640/480/city?4039\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-05-27T00:50:01.680Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 0.10732826530175998,\n" +
                    "\"description\": \"Iste nihil illo omnis quasi repellat facere. Consequuntur sit et sunt quae laboriosam non veniam quidem explicabo. Eveniet non molestiae quod repellendus esse tenetur. Impedit omnis et aliquid.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"0e1abf6d-ac08-4bf1-8032-b34a935aee67\",\n" +
                    "\"name\": \"rerum sit ut\",\n" +
                    "\"country\": \"Yemen\",\n" +
                    "\"city\": \"South Hershel\",\n" +
                    "\"price\": 497,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?7322\",\n" +
                    "\"http://lorempixel.com/640/480/city?50286\",\n" +
                    "\"http://lorempixel.com/640/480/city?3343\",\n" +
                    "\"http://lorempixel.com/640/480/city?22873\",\n" +
                    "\"http://lorempixel.com/640/480/city?39781\",\n" +
                    "\"http://lorempixel.com/640/480/city?65804\",\n" +
                    "\"http://lorempixel.com/640/480/city?26534\",\n" +
                    "\"http://lorempixel.com/640/480/city?68608\",\n" +
                    "\"http://lorempixel.com/640/480/city?88576\",\n" +
                    "\"http://lorempixel.com/640/480/city?83721\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-12-20T05:53:25.671Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 5.107155921056979,\n" +
                    "\"description\": \"Dolores facilis hic non et blanditiis. Hic id perferendis nesciunt occaecati. Voluptate provident quos possimus et voluptate aut illum qui. Natus illum minus sed.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"a1400d2d-06e0-438f-b1c1-5b86929319d0\",\n" +
                    "\"name\": \"inventore molestias aspernatur\",\n" +
                    "\"country\": \"Saint Lucia\",\n" +
                    "\"city\": \"Randallton\",\n" +
                    "\"price\": 884,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?87686\",\n" +
                    "\"http://lorempixel.com/640/480/city?46888\",\n" +
                    "\"http://lorempixel.com/640/480/city?70680\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-07-15T09:40:02.508Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 3.0014995475212847,\n" +
                    "\"description\": \"Laborum exercitationem esse harum in nihil maiores est. Ex saepe unde autem. Sed minus distinctio repellat corporis ipsam omnis ipsam cum ea. Rerum et illo ipsum alias aut quis deleniti cum esse. Sunt aliquid et repudiandae nulla sint impedit. Ex qui modi veniam esse.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ebc70814-0295-4748-8589-2c5efe485140\",\n" +
                    "\"name\": \"aut culpa ut\",\n" +
                    "\"country\": \"Madagascar\",\n" +
                    "\"city\": \"New Joanne\",\n" +
                    "\"price\": 202,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?20842\",\n" +
                    "\"http://lorempixel.com/640/480/city?86642\",\n" +
                    "\"http://lorempixel.com/640/480/city?68894\",\n" +
                    "\"http://lorempixel.com/640/480/city?36582\",\n" +
                    "\"http://lorempixel.com/640/480/city?57836\",\n" +
                    "\"http://lorempixel.com/640/480/city?38810\",\n" +
                    "\"http://lorempixel.com/640/480/city?82728\",\n" +
                    "\"http://lorempixel.com/640/480/city?70640\",\n" +
                    "\"http://lorempixel.com/640/480/city?19437\",\n" +
                    "\"http://lorempixel.com/640/480/city?7550\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-06-01T10:23:46.673Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 0.7572581035463175,\n" +
                    "\"description\": \"Aut qui dolores. Ipsa repellendus consequuntur. Libero voluptatem aliquam rerum dolore quis. Laborum voluptas cupiditate. Explicabo ut molestiae nemo culpa aut dolores asperiores eveniet.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d47a04bc-8eca-4951-bb7a-9dacbd508729\",\n" +
                    "\"name\": \"cumque voluptates reprehenderit\",\n" +
                    "\"country\": \"Jersey\",\n" +
                    "\"city\": \"Robelshire\",\n" +
                    "\"price\": 966,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?6865\",\n" +
                    "\"http://lorempixel.com/640/480/city?15523\",\n" +
                    "\"http://lorempixel.com/640/480/city?46071\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-06T15:46:49.471Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 2.4233793599416384,\n" +
                    "\"description\": \"Ad neque neque occaecati eos. Laudantium aut magni ex sint aut qui corporis animi. Ex alias iusto ut ut sit qui alias sit. Qui ut rerum. Quos magnam recusandae eveniet officia. Asperiores officiis voluptate consequuntur hic voluptate ea cupiditate.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"bb99a8b4-1b7b-45c7-a0e7-aedf5a15cc31\",\n" +
                    "\"name\": \"sit non ut\",\n" +
                    "\"country\": \"Romania\",\n" +
                    "\"city\": \"Lake Karineton\",\n" +
                    "\"price\": 941,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?91\",\n" +
                    "\"http://lorempixel.com/640/480/city?63272\",\n" +
                    "\"http://lorempixel.com/640/480/city?46723\",\n" +
                    "\"http://lorempixel.com/640/480/city?23985\",\n" +
                    "\"http://lorempixel.com/640/480/city?89776\",\n" +
                    "\"http://lorempixel.com/640/480/city?88077\",\n" +
                    "\"http://lorempixel.com/640/480/city?26326\",\n" +
                    "\"http://lorempixel.com/640/480/city?7997\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-22T13:59:32.116Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 3.8226472803427156,\n" +
                    "\"description\": \"Officia quia maiores doloribus et deleniti. At totam cupiditate sed nobis. Corrupti temporibus consequuntur autem est doloribus. Distinctio a debitis ad voluptatum eum modi placeat reprehenderit. Qui et nemo sit aut in est enim eos.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"bce9a4b9-811f-47ad-a6b6-0608e343e34d\",\n" +
                    "\"name\": \"dolore deleniti quia\",\n" +
                    "\"country\": \"Algeria\",\n" +
                    "\"city\": \"Wiegandland\",\n" +
                    "\"price\": 425,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?8022\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-09-07T08:08:31.742Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 3.4669163921731534,\n" +
                    "\"description\": \"Accusantium deserunt voluptatem autem nihil beatae dicta molestiae. Sint atque voluptatem molestias quis expedita. Ut inventore omnis voluptatem repellendus aut quo rerum voluptatem. Et sint soluta aut esse. Sit enim corporis quia. Aut accusantium qui ipsum.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"bb6cef7b-c9f6-478d-98d6-5c3d13e3140e\",\n" +
                    "\"name\": \"sit non vero\",\n" +
                    "\"country\": \"Angola\",\n" +
                    "\"city\": \"Horacioview\",\n" +
                    "\"price\": 255,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?73583\",\n" +
                    "\"http://lorempixel.com/640/480/city?61802\",\n" +
                    "\"http://lorempixel.com/640/480/city?61697\",\n" +
                    "\"http://lorempixel.com/640/480/city?98845\",\n" +
                    "\"http://lorempixel.com/640/480/city?15049\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-28T18:02:57.849Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 4.5096722793150334,\n" +
                    "\"description\": \"Tempore aperiam magnam qui quam qui amet ut quidem. Dignissimos in omnis ut placeat hic omnis quasi. Eos quas sed ad. Quidem quasi excepturi est autem laborum eius eum non quis. Consequuntur corporis quasi a quia aliquid qui est aspernatur.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"2ab99fda-8763-4c96-930f-56a1568662c7\",\n" +
                    "\"name\": \"corrupti vero veritatis\",\n" +
                    "\"country\": \"Cyprus\",\n" +
                    "\"city\": \"East Harrison\",\n" +
                    "\"price\": 249,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?79054\",\n" +
                    "\"http://lorempixel.com/640/480/city?58875\",\n" +
                    "\"http://lorempixel.com/640/480/city?21399\",\n" +
                    "\"http://lorempixel.com/640/480/city?58960\",\n" +
                    "\"http://lorempixel.com/640/480/city?47582\",\n" +
                    "\"http://lorempixel.com/640/480/city?93656\",\n" +
                    "\"http://lorempixel.com/640/480/city?83073\",\n" +
                    "\"http://lorempixel.com/640/480/city?5947\",\n" +
                    "\"http://lorempixel.com/640/480/city?7856\",\n" +
                    "\"http://lorempixel.com/640/480/city?50703\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-06-16T15:32:59.491Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 0.44695242518313805,\n" +
                    "\"description\": \"Aperiam ut repudiandae et ipsam. Natus quasi et. Est et deserunt odit dicta sapiente. Beatae vero neque delectus esse ipsa.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"c60aa49c-a3b7-4974-a716-0c0227f7e3e8\",\n" +
                    "\"name\": \"numquam debitis ab\",\n" +
                    "\"country\": \"United Arab Emirates\",\n" +
                    "\"city\": \"West Kristian\",\n" +
                    "\"price\": 412,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?41179\",\n" +
                    "\"http://lorempixel.com/640/480/city?92559\",\n" +
                    "\"http://lorempixel.com/640/480/city?94779\",\n" +
                    "\"http://lorempixel.com/640/480/city?11077\",\n" +
                    "\"http://lorempixel.com/640/480/city?74379\",\n" +
                    "\"http://lorempixel.com/640/480/city?39589\",\n" +
                    "\"http://lorempixel.com/640/480/city?93851\",\n" +
                    "\"http://lorempixel.com/640/480/city?92384\",\n" +
                    "\"http://lorempixel.com/640/480/city?65792\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-08T10:09:38.677Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 2.2002976120635522,\n" +
                    "\"description\": \"Error et veritatis ut dolorum impedit aut aut molestias. Libero voluptatum rerum provident. Id unde in delectus natus. Ad voluptatem laboriosam rerum nam esse magni non. Quasi sapiente ducimus. Animi at laborum voluptatum aperiam.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"339abef0-3dad-4c8d-a085-fadb9e264f0b\",\n" +
                    "\"name\": \"consequatur accusantium quaerat\",\n" +
                    "\"country\": \"French Guiana\",\n" +
                    "\"city\": \"Duncanfurt\",\n" +
                    "\"price\": 331,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?39683\",\n" +
                    "\"http://lorempixel.com/640/480/city?88309\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-08-17T05:27:01.154Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 0.7061860945130483,\n" +
                    "\"description\": \"Id dicta pariatur maiores et doloremque quia eveniet officiis. Voluptatem fugiat eaque quia dolorem aut quas fugiat exercitationem. Id omnis dolores iusto provident error. Blanditiis eum officiis.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ee80a1e5-0bb6-4a6e-8311-e4c7bd8e5f53\",\n" +
                    "\"name\": \"saepe facere quis\",\n" +
                    "\"country\": \"Morocco\",\n" +
                    "\"city\": \"Durganhaven\",\n" +
                    "\"price\": 304,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?35761\",\n" +
                    "\"http://lorempixel.com/640/480/city?84762\",\n" +
                    "\"http://lorempixel.com/640/480/city?77174\",\n" +
                    "\"http://lorempixel.com/640/480/city?44757\",\n" +
                    "\"http://lorempixel.com/640/480/city?1889\",\n" +
                    "\"http://lorempixel.com/640/480/city?34946\",\n" +
                    "\"http://lorempixel.com/640/480/city?75583\",\n" +
                    "\"http://lorempixel.com/640/480/city?65529\",\n" +
                    "\"http://lorempixel.com/640/480/city?79726\",\n" +
                    "\"http://lorempixel.com/640/480/city?46818\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-21T14:41:54.875Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 2.4165618837198295,\n" +
                    "\"description\": \"Iure assumenda qui cumque inventore dolores dignissimos. Laboriosam voluptate porro dolores qui itaque et ea architecto natus. Voluptates sit dolorem quibusdam culpa aut quo eaque omnis. At praesentium officiis eum beatae maiores aut autem. Est unde laboriosam vitae et velit consequuntur. Ipsa nesciunt libero earum consequuntur numquam quibusdam nobis vel dolorem.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"028c3368-3105-4faf-9a9a-b53d7f3f7c40\",\n" +
                    "\"name\": \"iusto ut natus\",\n" +
                    "\"country\": \"United Arab Emirates\",\n" +
                    "\"city\": \"Stanview\",\n" +
                    "\"price\": 744,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?37614\",\n" +
                    "\"http://lorempixel.com/640/480/city?3209\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-24T01:53:10.174Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 5.479390097840057,\n" +
                    "\"description\": \"Ducimus tempore a perferendis ea. Saepe eum est porro consequatur beatae assumenda eos accusantium qui. Accusantium tempore doloremque repellat dignissimos assumenda et quia.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"89cdf1d1-4658-4b42-83d5-aa51b512555b\",\n" +
                    "\"name\": \"reprehenderit optio ipsa\",\n" +
                    "\"country\": \"Brunei Darussalam\",\n" +
                    "\"city\": \"New Dorristown\",\n" +
                    "\"price\": 969,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?42944\",\n" +
                    "\"http://lorempixel.com/640/480/city?62965\",\n" +
                    "\"http://lorempixel.com/640/480/city?67029\",\n" +
                    "\"http://lorempixel.com/640/480/city?15060\",\n" +
                    "\"http://lorempixel.com/640/480/city?87912\",\n" +
                    "\"http://lorempixel.com/640/480/city?56005\",\n" +
                    "\"http://lorempixel.com/640/480/city?8696\",\n" +
                    "\"http://lorempixel.com/640/480/city?28678\",\n" +
                    "\"http://lorempixel.com/640/480/city?85911\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-03T14:48:15.026Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 0.08863193677388903,\n" +
                    "\"description\": \"Ut laboriosam est quo. Rerum voluptatem magni fuga officia deleniti totam itaque rerum. Ducimus nihil rerum omnis omnis molestias. Corporis eum iusto consectetur et. Laboriosam tempora numquam eum natus nobis error eum. Rem odio blanditiis rerum sit.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"d9b0ed86-a643-4f8c-a07f-0605db0645a4\",\n" +
                    "\"name\": \"blanditiis animi veritatis\",\n" +
                    "\"country\": \"Republic of Korea\",\n" +
                    "\"city\": \"Trevermouth\",\n" +
                    "\"price\": 235,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?32928\",\n" +
                    "\"http://lorempixel.com/640/480/city?65470\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-11-10T12:59:15.873Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 1.1975873997559119,\n" +
                    "\"description\": \"Molestiae non est aut cumque. Autem voluptatum et voluptas consequatur magnam sequi. Omnis quia assumenda et et eaque. At sunt et veritatis mollitia facilis doloremque autem alias. Qui enim reiciendis repudiandae vel sint. Odio quis quam in ut.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"6808d5be-d206-4cdb-b28a-e85360c31e93\",\n" +
                    "\"name\": \"numquam optio ipsam\",\n" +
                    "\"country\": \"Monaco\",\n" +
                    "\"city\": \"Lake Gennaro\",\n" +
                    "\"price\": 311,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?14696\",\n" +
                    "\"http://lorempixel.com/640/480/city?69245\",\n" +
                    "\"http://lorempixel.com/640/480/city?42762\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-09-23T00:54:40.571Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 5.325315843922393,\n" +
                    "\"description\": \"Et ipsa quisquam voluptas dolore voluptatibus et possimus. Perferendis id alias laborum. Rerum tempora nisi natus repellat labore aut perspiciatis velit laboriosam. Commodi voluptatem sed ab. Reprehenderit et perspiciatis vel autem. Officia tenetur molestiae enim asperiores rerum perferendis.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"addfdd35-80fd-440d-bb3b-6e49aa1d6343\",\n" +
                    "\"name\": \"eligendi saepe veritatis\",\n" +
                    "\"country\": \"Azerbaijan\",\n" +
                    "\"city\": \"South Kendrachester\",\n" +
                    "\"price\": 434,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?3687\",\n" +
                    "\"http://lorempixel.com/640/480/city?26160\",\n" +
                    "\"http://lorempixel.com/640/480/city?87640\",\n" +
                    "\"http://lorempixel.com/640/480/city?33588\",\n" +
                    "\"http://lorempixel.com/640/480/city?13038\",\n" +
                    "\"http://lorempixel.com/640/480/city?3070\",\n" +
                    "\"http://lorempixel.com/640/480/city?87416\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-31T00:54:29.011Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 3.377610525532023,\n" +
                    "\"description\": \"Cumque placeat quis itaque tempora modi illo aut. Consequatur fugiat iusto vero quibusdam. Illum rerum nihil assumenda repellat inventore soluta id. Error adipisci mollitia nam accusantium.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"76db00b6-f2b6-4802-b2b2-26ba543c3778\",\n" +
                    "\"name\": \"culpa cupiditate quis\",\n" +
                    "\"country\": \"Denmark\",\n" +
                    "\"city\": \"North Marian\",\n" +
                    "\"price\": 238,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?19565\",\n" +
                    "\"http://lorempixel.com/640/480/city?22004\",\n" +
                    "\"http://lorempixel.com/640/480/city?96294\",\n" +
                    "\"http://lorempixel.com/640/480/city?69540\",\n" +
                    "\"http://lorempixel.com/640/480/city?9674\",\n" +
                    "\"http://lorempixel.com/640/480/city?89061\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-08-09T10:17:56.726Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 3.2722927679686915,\n" +
                    "\"description\": \"Et quia culpa quia molestiae porro saepe. Qui perferendis ex. Et maxime et praesentium sunt velit cumque quod hic dignissimos. Et deleniti fuga a. Eum nemo modi beatae et ut. Ut voluptatem laborum sint.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"80f161c1-2be2-46cb-b9ff-71bc0376fcad\",\n" +
                    "\"name\": \"placeat fugiat officiis\",\n" +
                    "\"country\": \"Sierra Leone\",\n" +
                    "\"city\": \"Port Aiyanaview\",\n" +
                    "\"price\": 355,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?7104\",\n" +
                    "\"http://lorempixel.com/640/480/city?16544\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-07-23T11:56:22.601Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 0.2616498959726581,\n" +
                    "\"description\": \"Iure libero iusto facere aut beatae dicta pariatur. Facilis nulla unde sit est soluta. Earum ut voluptatum qui. Cupiditate necessitatibus provident sit sed cumque. Aut autem dicta aut excepturi. Dolorum nobis voluptas odit voluptas distinctio beatae nostrum.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"da5da01a-410d-44d5-90d5-2af980fc97f4\",\n" +
                    "\"name\": \"et dolore et\",\n" +
                    "\"country\": \"Czech Republic\",\n" +
                    "\"city\": \"South Levi\",\n" +
                    "\"price\": 665,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?32290\",\n" +
                    "\"http://lorempixel.com/640/480/city?75267\",\n" +
                    "\"http://lorempixel.com/640/480/city?36646\",\n" +
                    "\"http://lorempixel.com/640/480/city?41854\",\n" +
                    "\"http://lorempixel.com/640/480/city?85644\",\n" +
                    "\"http://lorempixel.com/640/480/city?56097\",\n" +
                    "\"http://lorempixel.com/640/480/city?95062\",\n" +
                    "\"http://lorempixel.com/640/480/city?12819\",\n" +
                    "\"http://lorempixel.com/640/480/city?66500\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-09T19:26:59.606Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 1.3481493518293206,\n" +
                    "\"description\": \"Vero ex ad qui ut natus aut eveniet iste. Distinctio itaque et est quia ducimus nihil recusandae. Ut optio sint. Vitae sequi quia odio illo praesentium consequatur sunt. Aut voluptatem est numquam accusamus. Saepe rem quisquam rerum nobis.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"8afc6cd6-5a62-47b3-bb35-a6274586a9b1\",\n" +
                    "\"name\": \"est porro rem\",\n" +
                    "\"country\": \"Burundi\",\n" +
                    "\"city\": \"North Nestor\",\n" +
                    "\"price\": 913,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?55661\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-08-05T06:25:00.616Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 1.5844913503598645,\n" +
                    "\"description\": \"Est nemo aut dignissimos accusamus vel nihil. Et harum sunt expedita voluptas aliquam cum. Ut animi est dolor ipsum quibusdam in. Repellat praesentium veritatis et culpa. Asperiores minus est modi officia ab.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"1f9746e6-25d4-47d6-9557-a3d75e3e9117\",\n" +
                    "\"name\": \"natus atque suscipit\",\n" +
                    "\"country\": \"Mexico\",\n" +
                    "\"city\": \"North Juliet\",\n" +
                    "\"price\": 625,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?93280\",\n" +
                    "\"http://lorempixel.com/640/480/city?57816\",\n" +
                    "\"http://lorempixel.com/640/480/city?77467\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-05-02T07:49:29.063Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 2.0621655414283486,\n" +
                    "\"description\": \"Dignissimos ullam temporibus. Distinctio deserunt qui animi harum minus sed. Fuga omnis molestiae incidunt commodi.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"81d69ae1-abbb-499b-8d36-eb3fc67995a9\",\n" +
                    "\"name\": \"velit voluptatem veritatis\",\n" +
                    "\"country\": \"Finland\",\n" +
                    "\"city\": \"Rohanland\",\n" +
                    "\"price\": 725,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?33551\",\n" +
                    "\"http://lorempixel.com/640/480/city?88002\",\n" +
                    "\"http://lorempixel.com/640/480/city?50525\",\n" +
                    "\"http://lorempixel.com/640/480/city?41918\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-04-07T13:23:32.649Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 4.006481958138446,\n" +
                    "\"description\": \"Cupiditate alias vitae sapiente aut debitis commodi. Quisquam vitae aut ipsum. Optio eum ut modi nam nisi adipisci nemo. Qui dolorem quod minus occaecati. Porro adipisci ut qui ad quasi facilis. Facere minus id velit inventore rerum dicta quas maxime.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ff7bc78b-4b0f-4c6e-a712-87fd6abdc9a7\",\n" +
                    "\"name\": \"est eum animi\",\n" +
                    "\"country\": \"Mali\",\n" +
                    "\"city\": \"East Autumnstad\",\n" +
                    "\"price\": 584,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?58268\",\n" +
                    "\"http://lorempixel.com/640/480/city?39833\",\n" +
                    "\"http://lorempixel.com/640/480/city?40570\",\n" +
                    "\"http://lorempixel.com/640/480/city?69355\",\n" +
                    "\"http://lorempixel.com/640/480/city?64400\",\n" +
                    "\"http://lorempixel.com/640/480/city?93699\",\n" +
                    "\"http://lorempixel.com/640/480/city?75398\",\n" +
                    "\"http://lorempixel.com/640/480/city?75798\",\n" +
                    "\"http://lorempixel.com/640/480/city?8742\",\n" +
                    "\"http://lorempixel.com/640/480/city?21300\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-04-05T23:31:25.416Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 0.9806608539429127,\n" +
                    "\"description\": \"Hic ut a eius nobis incidunt ipsum distinctio. Aliquam blanditiis et autem eligendi. Dolorem et ea. Dolor rerum ut eum quia et. Magnam libero et dolore quia a. Earum rerum et enim dignissimos velit quibusdam modi.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"511e419d-6b81-48cd-8046-9be3ddb0fbc2\",\n" +
                    "\"name\": \"quia et recusandae\",\n" +
                    "\"country\": \"Vanuatu\",\n" +
                    "\"city\": \"Audreannechester\",\n" +
                    "\"price\": 419,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?42560\",\n" +
                    "\"http://lorempixel.com/640/480/city?15592\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-06-18T11:37:18.956Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 5.723448492217518,\n" +
                    "\"description\": \"Tenetur debitis praesentium consequuntur id repellendus excepturi autem quidem. Esse quae tempora eveniet assumenda dolore quam corrupti. Reiciendis et rerum.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"bbf9139f-fe47-4402-9f6b-33a01c57de7d\",\n" +
                    "\"name\": \"perferendis deleniti eveniet\",\n" +
                    "\"country\": \"Micronesia\",\n" +
                    "\"city\": \"Beahanbury\",\n" +
                    "\"price\": 268,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?66836\",\n" +
                    "\"http://lorempixel.com/640/480/city?55262\",\n" +
                    "\"http://lorempixel.com/640/480/city?4522\",\n" +
                    "\"http://lorempixel.com/640/480/city?896\",\n" +
                    "\"http://lorempixel.com/640/480/city?4603\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-12-04T00:13:38.546Z\",\n" +
                    "\"stars\": 4,\n" +
                    "\"rating\": 5.0272499992723265,\n" +
                    "\"description\": \"Ipsam odio accusantium. Voluptatem in vero atque. Veniam magni vel non quo assumenda perferendis. Consectetur ducimus voluptatem sed consequuntur repellat doloremque nulla. Et qui beatae a laboriosam sed vel id consequatur. Consectetur dolore quasi quae consequatur.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"50976428-3914-431a-96dd-698c67595220\",\n" +
                    "\"name\": \"dolor explicabo beatae\",\n" +
                    "\"country\": \"Ghana\",\n" +
                    "\"city\": \"Violaview\",\n" +
                    "\"price\": 490,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?18260\",\n" +
                    "\"http://lorempixel.com/640/480/city?1014\",\n" +
                    "\"http://lorempixel.com/640/480/city?68364\",\n" +
                    "\"http://lorempixel.com/640/480/city?54507\",\n" +
                    "\"http://lorempixel.com/640/480/city?68866\",\n" +
                    "\"http://lorempixel.com/640/480/city?79632\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-02-05T08:23:29.507Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 0.3760873747169269,\n" +
                    "\"description\": \"Iste impedit ratione ducimus blanditiis iusto magni. Est numquam est ut. Sed sit facere consequatur consequatur vel enim debitis est.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"af9d9d0b-4eae-405a-ad57-7fd44c494f2f\",\n" +
                    "\"name\": \"aut illo eaque\",\n" +
                    "\"country\": \"Mexico\",\n" +
                    "\"city\": \"Alysafurt\",\n" +
                    "\"price\": 253,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?65768\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2018-10-28T18:47:52.144Z\",\n" +
                    "\"stars\": 2,\n" +
                    "\"rating\": 4.670452883233546,\n" +
                    "\"description\": \"Modi dolor omnis voluptatem qui. Debitis reiciendis illum quis alias esse labore vel. Soluta delectus ut hic accusantium id. Odit voluptatibus amet et architecto. Recusandae facere perspiciatis recusandae iste corporis distinctio.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"c70aa0fc-4366-4b9a-9d42-f8dae5f9181a\",\n" +
                    "\"name\": \"vel tenetur voluptates\",\n" +
                    "\"country\": \"Lao People's Democratic Republic\",\n" +
                    "\"city\": \"Angeloport\",\n" +
                    "\"price\": 225,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?89767\",\n" +
                    "\"http://lorempixel.com/640/480/city?76678\",\n" +
                    "\"http://lorempixel.com/640/480/city?41186\",\n" +
                    "\"http://lorempixel.com/640/480/city?85303\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-01-08T08:22:10.536Z\",\n" +
                    "\"stars\": 1,\n" +
                    "\"rating\": 0.5190949640372287,\n" +
                    "\"description\": \"Amet iure iusto vero rerum. Sit voluptas est vel et maiores quis aut. Et maxime velit tempora aut dolorem dolor culpa laborum ex. Quia alias veniam perferendis temporibus dolore pariatur dolore. Debitis dolores eius est ullam natus placeat.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"ede2d137-7cea-4741-b41d-64942de186df\",\n" +
                    "\"name\": \"excepturi animi suscipit\",\n" +
                    "\"country\": \"Norway\",\n" +
                    "\"city\": \"North Lawrenceview\",\n" +
                    "\"price\": 408,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?97667\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-07T10:00:31.054Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 4.416579455396564,\n" +
                    "\"description\": \"Neque id reiciendis laudantium dicta exercitationem earum vel quam. Ad reiciendis eum velit. Recusandae dolores ad aliquid id dolor illo placeat. Reprehenderit earum incidunt temporibus exercitationem sed natus qui. Dicta nemo facilis. Adipisci optio eum voluptas incidunt a numquam quia et corrupti.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"e4cf98ce-71ae-40fe-bee6-2bb618722520\",\n" +
                    "\"name\": \"id minus natus\",\n" +
                    "\"country\": \"Solomon Islands\",\n" +
                    "\"city\": \"Hassieshire\",\n" +
                    "\"price\": 344,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?75106\",\n" +
                    "\"http://lorempixel.com/640/480/city?31338\",\n" +
                    "\"http://lorempixel.com/640/480/city?10171\",\n" +
                    "\"http://lorempixel.com/640/480/city?94443\",\n" +
                    "\"http://lorempixel.com/640/480/city?34197\",\n" +
                    "\"http://lorempixel.com/640/480/city?50158\",\n" +
                    "\"http://lorempixel.com/640/480/city?23558\",\n" +
                    "\"http://lorempixel.com/640/480/city?8408\",\n" +
                    "\"http://lorempixel.com/640/480/city?6876\",\n" +
                    "\"http://lorempixel.com/640/480/city?33491\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-04T18:57:59.559Z\",\n" +
                    "\"stars\": 5,\n" +
                    "\"rating\": 4.80397464084942,\n" +
                    "\"description\": \"Nam voluptatem earum. Aut dolorem repellendus blanditiis consectetur commodi reprehenderit ipsum officiis modi. Exercitationem totam expedita recusandae omnis iure veritatis exercitationem sed.\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"id\": \"12a4543b-02fb-41f8-a9d1-aafed75594be\",\n" +
                    "\"name\": \"minima dolorem eos\",\n" +
                    "\"country\": \"Niue\",\n" +
                    "\"city\": \"Herminaton\",\n" +
                    "\"price\": 676,\n" +
                    "\"images\": [\n" +
                    "\"http://lorempixel.com/640/480/city?14023\",\n" +
                    "\"http://lorempixel.com/640/480/city?99663\",\n" +
                    "\"http://lorempixel.com/640/480/city?57911\"\n" +
                    "],\n" +
                    "\"date_start\": \"2018-06-01T17:59:31.740Z\",\n" +
                    "\"date_end\": \"2019-03-24T10:25:28.992Z\",\n" +
                    "\"stars\": 3,\n" +
                    "\"rating\": 4.072365436685519,\n" +
                    "\"description\": \"Omnis non explicabo in. Aspernatur perferendis nihil earum commodi. Enim voluptates molestiae adipisci fugiat dolorem suscipit. Eius et consequatur velit quibusdam. Qui quidem exercitationem ut ipsam ut dolorum.\"\n" +
                    "}\n" +
                    "]");
            for (int i = 0; i < body.length(); i++) {
                ArrayList<String> Images = new ArrayList<String>();
                JSONObject item = body.getJSONObject(i);
                JSONArray images = item.getJSONArray("images");
                for (int img = 0; img < images.length(); img++) {
                    Images.add(images.getString(img));
                }
                hotels.add(new Hotels(item.getString("name"),
                        item.getString("country"),
                        item.getString("city"),
                        item.getString("description"),
                        item.getDouble("price"),
                        item.getDouble("stars"),
                        item.getDouble("rating"),
                        Images
                ));
            }
        } catch (JSONException e) {
            Log.i("eeee", e.getMessage());
        }

        adapter = new HotelsAdapter(HotelsActivity.this, R.layout.grid_hotels_layout);
        adapter.hotels = hotels;
        gvHotels.setAdapter(adapter);
*/


        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("errori",e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("s",response.toString());
                    throw new IOException("Unexpected code " + response);

                } else{
                        try {
                            JSONArray body = new JSONArray(response.body().string());
                            for (int i = 0; i < 10; i++) {
                                ArrayList<String> Images = new ArrayList<String>();
                                JSONObject item = body.getJSONObject(i);
                                JSONArray images = item.getJSONArray("images");
                                for (int img = 0; img < images.length(); img++) {
                                    Images.add(images.getString(img));
                                }
                                hotels.add(new Hotels(item.getString("name"),
                                        item.getString("country"),
                                        item.getString("city"),
                                        item.getString("description"),
                                        item.getDouble("price"),
                                        item.getDouble("stars"),
                                        item.getDouble("rating"),
                                        Images
                                ));
                            }
                        } catch (JSONException e) {
                            Log.i("eeee", e.getMessage());
                            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                        HotelsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new HotelsAdapter(HotelsActivity.this, R.layout.grid_hotels_layout);
                                adapter.hotels = hotels;
                                gvHotels.setAdapter(adapter);
                                Toast.makeText(HotelsActivity.this,"sadsadsad",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
            }

        });
        //details
        gvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),HotelDetailsActivity.class);


            }
        });
    }

}
