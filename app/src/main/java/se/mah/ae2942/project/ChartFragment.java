package se.mah.ae2942.project;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;

/**
 * Google Chart fragment, summarizes the expenses in a donut diagram..
 * Worked on: Ragnar Einestam
 */
public class ChartFragment extends Fragment {

    private View view;
    private Controller controller;
    private PieChart pieChart;
    private String[] xData = {"Entertainment", "Home", "Travel", "Food", "Other"};
    private float[] yData = {200, 242, 234, 235, 111};

    /**
     * Constructor
     */
    public ChartFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, container, false);
        initiate();
        return view;

    }

    public void initiate() {
        pieChart = (PieChart)view.findViewById(R.id.fragment_chart_piechart);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Summary Expenses");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(7);
        pieChart.setCenterText("Expenses");
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setOnChartValueSelectedListener(new ChartListener());
        addExpensesToChart();

        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);

    }

    /**
     * Sets local controller.
     * @param controller input
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * Adds expenses to the pieChart.
     */
    public void addExpensesToChart(){

        ArrayList<Entry> yList = new ArrayList<Entry>();

        for(int i = 0; i < yData.length; i++){
            yList.add(new Entry(yData[i], i));
        }

        ArrayList<String> xList = new ArrayList<String>();

        for(int j = 0; j < xData.length; j++){
            xList.add(xData[j]);
        }

    }

    public void dataSet(Entry xList, String yList){
        //PieDataSet dataSet = new PieDataSet(yData, "asd");

    }

    private class ChartListener implements com.github.mikephil.charting.listener.OnChartValueSelectedListener{

        public void onValueSelected(Entry entry, int position, Highlight timeHighlighted) {
            Toast.makeText(getActivity(), entry.toString(), Toast.LENGTH_SHORT).show();
        }

        public void onNothingSelected() {

        }
    }
}