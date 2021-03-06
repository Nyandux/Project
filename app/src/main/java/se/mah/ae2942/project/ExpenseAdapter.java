package se.mah.ae2942.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter for the ListView in ListFragment.
 * Worked on: Ragnar Einestam
 */
public class ExpenseAdapter extends ArrayAdapter<Expense> {

    /**
     * Constructor
     * @param context view
     * @param expenses expenses array
     */
    public ExpenseAdapter(Context context, Expense[] expenses) {
        super(context, R.layout.adapter_expenses, expenses);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_expenses, parent, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.adapter_list_layout_textview_title);
        TextView tvAmount = (TextView) view.findViewById(R.id.adapter_list_layout_textview_amount);
        TextView tvDate = (TextView) view.findViewById(R.id.adapter_list_layout_textview_date);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.adapter_list_layout_imageview);

        Expense expense = getItem(position);

        tvTitle.setText(expense.getTitle());
        tvAmount.setText(String.valueOf(expense.getAmount()));
        tvDate.setText(expense.getDate());

        switch (expense.getCategory()) {

            case "Food":
                ivIcon.setImageResource(R.drawable.food);
                break;
            case "Other":
                ivIcon.setImageResource(R.drawable.other);
                break;
            case "Travel":
                ivIcon.setImageResource(R.drawable.travel);
                break;
            case "Home":
                ivIcon.setImageResource(R.drawable.home);
                break;
            case "Entertainment":
                ivIcon.setImageResource(R.drawable.entertainmant);
        }
        return view;
    }
}
