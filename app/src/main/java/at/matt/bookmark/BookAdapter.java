package at.matt.bookmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    Context context;
    int layoutResourceId;
    List<Book> books = null;

    public BookAdapter(Context context, int layoutResourceId, List<Book> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.books = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater = ((MainActivity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new BookHolder();
            holder.page = (TextView)convertView.findViewById(R.id.lv_page);
            holder.title = (TextView)convertView.findViewById(R.id.lv_title);
            holder.author = (TextView)convertView.findViewById(R.id.lv_author);

            convertView.setTag(holder);
        }
        else
        {
            holder = (BookHolder)convertView.getTag();
        }

        Book book = books.get(position);
        int i = book.getId().getBytes()[0];
        if(i%5 == 0)
            convertView.findViewById(R.id.page_layout).setBackgroundColor(getContext().getResources().getColor(R.color.red));
        if(i%5 == 1)
            convertView.findViewById(R.id.page_layout).setBackgroundColor(getContext().getResources().getColor(R.color.orange));
        if(i%5 == 2)
            convertView.findViewById(R.id.page_layout).setBackgroundColor(getContext().getResources().getColor(R.color.green));
        if(i%5 == 3)
            convertView.findViewById(R.id.page_layout).setBackgroundColor(getContext().getResources().getColor(R.color.blue));
        if(i%5 == 4)
            convertView.findViewById(R.id.page_layout).setBackgroundColor(getContext().getResources().getColor(R.color.purple));
        holder.title.setText(book.getTitle());
        holder.page.setText(book.getPage());
        holder.author.setText(book.getAuthor());

        return convertView;
    }

    static class BookHolder
    {
        TextView page;
        TextView title;
        TextView author;
    }
}