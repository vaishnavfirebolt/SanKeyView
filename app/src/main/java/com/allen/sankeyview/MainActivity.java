package com.allen.sankeyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allen.sankeyview.model.SanKeyModel;
import com.allen.sankeyview.view.SanKeyGroup;
import com.allen.sankeyview.view.SanKeyView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SanKeyView sanKeyView;
    private SanKeyGroup sanKeyGroup;
    private SanKeyModel mode0;
    private SanKeyModel mode1;
    private SanKeyModel mode2;
    private Button btn0;
    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    private void initData() {



    }

    private void initView() {
        sanKeyView = findViewById(R.id.san_key_view);
        sanKeyGroup = findViewById(R.id.san_key_group);
        btn0 = findViewById(R.id.data0);
        btn1 = findViewById(R.id.data1);
        btn2 = findViewById(R.id.data2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data0:
                Gson gson0 = new Gson();
                mode0 = gson0.fromJson(data0, SanKeyModel.class);
                sanKeyGroup.setData(mode0);
                break;
            case R.id.data1:

                Gson gson1 = new Gson();
                mode1 = gson1.fromJson(data1, SanKeyModel.class);
                sanKeyGroup.setData(mode1);
                break;
            case R.id.data2:
                Gson gson2 = new Gson();
                mode2 = gson2.fromJson(data2, SanKeyModel.class);
                sanKeyGroup.setData(mode2);
                break;


        }
    }

    private String data1 = "{\"nodes\":[{\"name\": \"a\"}, {\"name\": \"b\"}, {\"name\": \"a1\"}, {\"name\": \"a2\"}, {\"name\": \"b1\"}, {\"name\": \"c\"}]," +
            "\"links\": [{\"source\": \"a\",\"target\": \"a1\",\"value\": 5}," +
            " {\"source\": \"a\",\"target\": \"a2\",\"value\": 3}, {\"source\": \"b\",\"target\": \"b1\",value: 8}, {\"source\": \"a\",\"target\": \"b1\",\"value\": 3}," +
            " {\"source\": \"b1\",\"target\": \"a1\",value: 1}, {\"source\": \"b1\",\"target\": \"c\",\"value\": 2}]}";
    private String data2 = "{\"nodes\":[\n" +
            "{\"name\":\"Agricultural 'waste'\"},\n" +
            "{\"name\":\"Bio-conversion\"},\n" +
            "{\"name\":\"Liquid\"},\n" +
            "{\"name\":\"Losses\"},\n" +
            "{\"name\":\"Solid\"},\n" +
            "{\"name\":\"Gas\"},\n" +
            "{\"name\":\"Biofuel imports\"},\n" +
            "{\"name\":\"Biomass imports\"},\n" +
            "{\"name\":\"Coal imports\"},\n" +
            "{\"name\":\"Coal\"},\n" +
            "{\"name\":\"Coal reserves\"},\n" +
            "{\"name\":\"District heating\"},\n" +
            "{\"name\":\"Industry\"},\n" +
            "{\"name\":\"Heating and cooling - commercial\"},\n" +
            "{\"name\":\"Heating and cooling - homes\"},\n" +
            "{\"name\":\"Electricity grid\"},\n" +
            "{\"name\":\"Over generation / exports\"},\n" +
            "{\"name\":\"H2 conversion\"},\n" +
            "{\"name\":\"Road transport\"},\n" +
            "{\"name\":\"Agriculture\"},\n" +
            "{\"name\":\"Rail transport\"},\n" +
            "{\"name\":\"Lighting & appliances - commercial\"},\n" +
            "{\"name\":\"Lighting & appliances - homes\"},\n" +
            "{\"name\":\"Gas imports\"},\n" +
            "{\"name\":\"Ngas\"},\n" +
            "{\"name\":\"Gas reserves\"},\n" +
            "{\"name\":\"Thermal generation\"},\n" +
            "{\"name\":\"Geothermal\"},\n" +
            "{\"name\":\"H2\"},\n" +
            "{\"name\":\"Hydro\"},\n" +
            "{\"name\":\"International shipping\"},\n" +
            "{\"name\":\"Domestic aviation\"},\n" +
            "{\"name\":\"International aviation\"},\n" +
            "{\"name\":\"National navigation\"},\n" +
            "{\"name\":\"Marine algae\"},\n" +
            "{\"name\":\"Nuclear\"},\n" +
            "{\"name\":\"Oil imports\"},\n" +
            "{\"name\":\"Oil\"},\n" +
            "{\"name\":\"Oil reserves\"},\n" +
            "{\"name\":\"Other waste\"},\n" +
            "{\"name\":\"Pumped heat\"},\n" +
            "{\"name\":\"Solar PV\"},\n" +
            "{\"name\":\"Solar Thermal\"},\n" +
            "{\"name\":\"Solar\"},\n" +
            "{\"name\":\"Tidal\"},\n" +
            "{\"name\":\"UK land based bioenergy\"},\n" +
            "{\"name\":\"Wave\"},\n" +
            "{\"name\":\"Wind\"}\n" +
            "],\n" +
            "\"links\":[\n" +
            "{\"source\": \"Agricultural 'waste'\", \"target\": \"Bio-conversion\",  \"value\": 124.729},\n" +
            "{\"source\": \"Bio-conversion\", \"target\": \"Liquid\",  \"value\": 0.597},\n" +
            "{\"source\": \"Bio-conversion\",  \"target\": \"Losses\",  \"value\": 26.862},\n" +
            "{\"source\": \"Bio-conversion\",  \"target\": \"Solid\",  \"value\": 280.322},\n" +
            "{\"source\": \"Bio-conversion\", \"target\": \"Gas\",  \"value\": 81.144},\n" +
            "{\"source\": \"Biofuel imports\", \"target\": \"Liquid\",  \"value\": 35},\n" +
            "{\"source\": \"Biomass imports\", \"target\": \"Solid\",  \"value\": 35},\n" +
            "{\"source\": \"Coal imports\", \"target\": \"Coal\", \"value\": 11.606},\n" +
            "{\"source\": \"Coal reserves\",\"target\": \"Coal\", \"value\": 63.965},\n" +
            "{\"source\": \"Coal\", \"target\": \"Solid\", \"value\": 75.571},\n" +
            "{\"source\": \"District heating\", \"target\": \"Industry\", \"value\": 10.639},\n" +
            "{\"source\": \"District heating\", \"target\": \"Heating and cooling - commercial\", \"value\": 22.505},\n" +
            "{\"source\": \"District heating\", \"target\": \"Heating and cooling - homes\", \"value\": 46.184},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Over generation / exports\", \"value\": 104.453},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Heating and cooling - homes\", \"value\": 113.726},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"H2 conversion\", \"value\": 27.14},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Industry\", \"value\": 342.165},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Road transport\", \"value\": 37.797},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Agriculture\", \"value\": 4.412},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Heating and cooling - commercial\", \"value\": 40.858},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Losses\", \"value\": 56.691},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Rail transport\", \"value\": 7.863},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Lighting & appliances - commercial\", \"value\": 90.008},\n" +
            "{\"source\": \"Electricity grid\", \"target\": \"Lighting & appliances - homes\", \"value\": 93.494},\n" +
            "{\"source\": \"Gas imports\", \"target\": \"Ngas\", \"value\": 40.719},\n" +
            "{\"source\": \"Gas reserves\", \"target\": \"Ngas\", \"value\": 82.233},\n" +
            "{\"source\": \"Gas\", \"target\": \"Heating and cooling - commercial\", \"value\": 0.129},\n" +
            "{\"source\": \"Gas\", \"target\": \"Losses\", \"value\": 1.401},\n" +
            "{\"source\": \"Gas\", \"target\": \"Thermal generation\", \"value\": 151.891},\n" +
            "{\"source\": \"Gas\", \"target\": \"Agriculture\", \"value\": 2.096},\n" +
            "{\"source\": \"Gas\", \"target\": \"Industry\", \"value\": 48.58},\n" +
            "{\"source\": \"Geothermal\", \"target\": \"Electricity grid\", \"value\": 7.013},\n" +
            "{\"source\": \"H2 conversion\", \"target\": \"H2\", \"value\": 20.897},\n" +
            "{\"source\": \"H2 conversion\", \"target\": \"Losses\", \"value\": 6.242},\n" +
            "{\"source\": \"H2\", \"target\": \"Road transport\", \"value\": 20.897},\n" +
            "{\"source\": \"Hydro\", \"target\": \"Electricity grid\", \"value\": 6.995},\n" +
            "{\"source\": \"Liquid\", \"target\": \"Industry\", \"value\": 121.066},\n" +
            "{\"source\": \"Liquid\", \"target\": \"International shipping\", \"value\": 128.69},\n" +
            "{\"source\": \"Liquid\", \"target\": \"Road transport\", \"value\": 135.835},\n" +
            "{\"source\": \"Liquid\", \"target\": \"Domestic aviation\", \"value\": 14.458},\n" +
            "{\"source\": \"Liquid\", \"target\": \"International aviation\", \"value\": 206.267},\n" +
            "{\"source\": \"Liquid\", \"target\": \"Agriculture\", \"value\": 3.64},\n" +
            "{\"source\": \"Liquid\", \"target\": \"National navigation\", \"value\": 33.218},\n" +
            "{\"source\": \"Liquid\", \"target\": \"Rail transport\", \"value\": 4.413},\n" +
            "{\"source\": \"Marine algae\", \"target\": \"Bio-conversion\", \"value\": 4.375},\n" +
            "{\"source\": \"Ngas\", \"target\": \"Gas\", \"value\": 122.952},\n" +
            "{\"source\": \"Nuclear\", \"target\": \"Thermal generation\", \"value\": 839.978},\n" +
            "{\"source\": \"Oil imports\", \"target\": \"Oil\", \"value\": 504.287},\n" +
            "{\"source\": \"Oil reserves\", \"target\": \"Oil\", \"value\": 107.703},\n" +
            "{\"source\": \"Oil\", \"target\": \"Liquid\", \"value\": 611.99},\n" +
            "{\"source\": \"Other waste\", \"target\": \"Solid\", \"value\": 56.587},\n" +
            "{\"source\": \"Other waste\", \"target\": \"Bio-conversion\", \"value\": 77.81},\n" +
            "{\"source\": \"Pumped heat\", \"target\": \"Heating and cooling - homes\", \"value\": 193.026},\n" +
            "{\"source\": \"Pumped heat\", \"target\": \"Heating and cooling - commercial\", \"value\": 70.672},\n" +
            "{\"source\": \"Solar PV\", \"target\": \"Electricity grid\", \"value\": 59.901},\n" +
            "{\"source\": \"Solar Thermal\", \"target\": \"Heating and cooling - homes\", \"value\": 19.263},\n" +
            "{\"source\": \"Solar\", \"target\": \"Solar Thermal\", \"value\": 19.263},\n" +
            "{\"source\": \"Solar\", \"target\": \"Solar PV\", \"value\": 59.901},\n" +
            "{\"source\": \"Solid\", \"target\": \"Agriculture\", \"value\": 0.882},\n" +
            "{\"source\": \"Solid\", \"target\": \"Thermal generation\", \"value\": 400.12},\n" +
            "{\"source\": \"Solid\", \"target\": \"Industry\", \"value\": 46.477},\n" +
            "{\"source\": \"Thermal generation\", \"target\": \"Electricity grid\", \"value\": 525.531},\n" +
            "{\"source\": \"Thermal generation\", \"target\": \"Losses\", \"value\": 787.129},\n" +
            "{\"source\": \"Thermal generation\", \"target\": \"District heating\", \"value\": 79.329},\n" +
            "{\"source\": \"Tidal\", \"target\": \"Electricity grid\", \"value\": 9.452},\n" +
            "{\"source\": \"UK land based bioenergy\", \"target\": \"Bio-conversion\", \"value\": 182.01},\n" +
            "{\"source\": \"Wave\", \"target\": \"Electricity grid\", \"value\": 19.013},\n" +
            "{\"source\": \"Wind\", \"target\": \"Electricity grid\", \"value\": 289.366}\n" +
            "]}";

    private String data0 = "{\"nodes\": [\n" +
            "      {\"name\": \"Total\"},\n" +
            "      {\"name\": \"Environment\"},\n" +
            "      {\"name\": \"Land use\"},\n" +
            "      {\"name\": \"Cocoa butter (Organic)\"},\n" +
            "      {\"name\": \"Cocoa mass (Organic)\"},\n" +
            "      {\"name\": \"Hazelnuts (Organic)\"},\n" +
            "      {\"name\": \"Cane sugar (Organic)\"},\n" +
            "      {\"name\": \"Vegetables (Organic)\"},\n" +
            "      {\"name\": \"Climate change\"},\n" +
            "      {\"name\": \"Harmful substances\"},\n" +
            "      {\"name\": \"Water use\"},\n" +
            "      {\"name\": \"Resource depletion\"},\n" +
            "      {\"name\": \"Refrigeration\"},\n" +
            "      {\"name\": \"Packaging\"},\n" +
            "      {\"name\": \"Human rights\"},\n" +
            "      {\"name\": \"Child labour\"},\n" +
            "      {\"name\": \"Coconut oil (Organic)\"},\n" +
            "      {\"name\": \"Forced labour\"},\n" +
            "      {\"name\": \"Health safety\"},\n" +
            "      {\"name\": \"Access to water\"},\n" +
            "      {\"name\": \"Freedom of association\"},\n" +
            "      {\"name\": \"Access to land\"},\n" +
            "      {\"name\": \"Sufficient wage\"},\n" +
            "      {\"name\": \"Equal rights migrants\"},\n" +
            "      {\"name\": \"Discrimination\"},\n" +
            "      {\"name\": \"Working hours\"}\n" +
            "   ],\n" +
            "   \"links\": [\n" +
            "      {\"source\": \"Total\", \"target\": \"Environment\", \"value\": 0.342284047256003},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Land use\", \"value\": 0.32322870366987},\n" +
            "      {\"source\": \"Land use\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.177682517071359},\n" +
            "      {\"source\": \"Land use\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.137241325342711},\n" +
            "      {\"source\": \"Land use\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.00433076373512774},\n" +
            "      {\"source\": \"Land use\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00296956039863467},\n" +
            "      {\"source\": \"Land use\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00100453712203756},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Climate change\", \"value\": 0.0112886157414413},\n" +
            "      {\"source\": \"Climate change\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.00676852971933996},\n" +
            "      {\"source\": \"Climate change\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00394686874786743},\n" +
            "      {\"source\": \"Climate change\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.000315972058711838},\n" +
            "      {\"source\": \"Climate change\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.000218969462265292},\n" +
            "      {\"source\": \"Climate change\", \"target\": \"Vegetables (Organic)\", \"value\": 3.82757532567656e-05},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Harmful substances\", \"value\": 0.00604275542495656},\n" +
            "      {\"source\": \"Harmful substances\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.0055125989240741},\n" +
            "      {\"source\": \"Harmful substances\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.000330017607892127},\n" +
            "      {\"source\": \"Harmful substances\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.000200138892990337},\n" +
            "      {\"source\": \"Harmful substances\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Harmful substances\", \"target\": \"Vegetables (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Water use\", \"value\": 0.00148345269044703},\n" +
            "      {\"source\": \"Water use\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.00135309891304186},\n" +
            "      {\"source\": \"Water use\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.000105714137908639},\n" +
            "      {\"source\": \"Water use\", \"target\": \"Hazelnuts (Organic)\", \"value\": 1.33452642581887e-05},\n" +
            "      {\"source\": \"Water use\", \"target\": \"Cane sugar (Organic)\", \"value\": 8.78074837009238e-06},\n" +
            "      {\"source\": \"Water use\", \"target\": \"Vegetables (Organic)\", \"value\": 2.5136268682477e-06},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Resource depletion\", \"value\": 0.000240519729288764},\n" +
            "      {\"source\": \"Resource depletion\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.000226237279345084},\n" +
            "      {\"source\": \"Resource depletion\", \"target\": \"Vegetables (Organic)\", \"value\": 1.42824499436793e-05},\n" +
            "      {\"source\": \"Resource depletion\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Resource depletion\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Resource depletion\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Refrigeration\", \"value\": 0},\n" +
            "      {\"source\": \"Environment\", \"target\": \"Packaging\", \"value\": 0},\n" +
            "      {\"source\": \"Total\", \"target\": \"Human rights\", \"value\": 0.307574096993239},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Child labour\", \"value\": 0.0410641202645833},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0105339381639722},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.0105},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0087294420777},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00474399974233333},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00388226450884445},\n" +
            "      {\"source\": \"Child labour\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00267447577173333},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Forced labour\", \"value\": 0.0365458590642445},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0114913076376389},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0081134807347},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00765230236575},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.004},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00296668823626667},\n" +
            "      {\"source\": \"Forced labour\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00232208008988889},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Health safety\", \"value\": 0.0345435327843611},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0121419536385},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00766772850678333},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0056245892061},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00361616847688889},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00277374682533333},\n" +
            "      {\"source\": \"Health safety\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00271934613075556},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Access to water\", \"value\": 0.0340206659360667},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.0105},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0089274160792},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0054148022845},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00333938149786667},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00314663377488889},\n" +
            "      {\"source\": \"Access to water\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00269243229961111},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Freedom of association\", \"value\": 0.0320571523941667},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0132312483463611},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0077695200707},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00510606573995},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00354321156324444},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00240710667391111},\n" +
            "      {\"source\": \"Freedom of association\", \"target\": \"Coconut oil (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Access to land\", \"value\": 0.0315022209894056},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.00964970063322223},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00938530207965},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0060110791848},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00380818314608889},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00264795594564445},\n" +
            "      {\"source\": \"Access to land\", \"target\": \"Coconut oil (Organic)\", \"value\": 0},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Sufficient wage\", \"value\": 0.0287776757227333},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00883512456493333},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0078343367268},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00347879026511111},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.00316254211388889},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00281013722808889},\n" +
            "      {\"source\": \"Sufficient wage\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00265674482391111},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Equal rights migrants\", \"value\" : 0.0271146645119444},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0071042315061},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00636673210005},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.00601459775836111},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00429185583138889},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00182647471915556},\n" +
            "      {\"source\": \"Equal rights migrants\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00151077259688889},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Discrimination\", \"value\": 0.0211217763359833},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00609671700306667},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0047738806325},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00368119084494444},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00286009813604444},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.00283706180951111},\n" +
            "      {\"source\": \"Discrimination\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.000872827909916666},\n" +
            "      {\"source\": \"Human rights\", \"target\": \"Working hours\", \"value\": 0.02082642898975},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Hazelnuts (Organic)\", \"value\": 0.0107216773848333},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Coconut oil (Organic)\", \"value\": 0.00359009052944444},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Vegetables (Organic)\", \"value\": 0.00212300379075556},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Cocoa butter (Organic)\", \"value\": 0.0018518584356},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Cocoa mass (Organic)\", \"value\": 0.00158227069058333},\n" +
            "      {\"source\": \"Working hours\", \"target\": \"Cane sugar (Organic)\", \"value\": 0.000957528158533333}\n" +
            "   ]}\n";
}
