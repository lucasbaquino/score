package lucasborges.mobi.scores.features.scores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lucasborges.mobi.scores.R;
import lucasborges.mobi.scores.models.Score;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class ScoresRecyclerViewAdapter extends RecyclerView.Adapter<ScoresRecyclerViewAdapter.ScoreViewHolder> {

    private List<Score> scoreModelList;

    public ScoresRecyclerViewAdapter(List<Score> scoreModelList){
        this.scoreModelList = scoreModelList;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ScoreViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_score, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder viewHolder, int pos) {
        Score scoreModel = scoreModelList.get(pos);
        viewHolder.getTxtGoalsAway().setText(String.valueOf(scoreModel.getGoalsAwayTeam()));
        viewHolder.getTxtGoalsHome().setText(String.valueOf(scoreModel.getGoalsHomeTeam()));
        viewHolder.getTxtDate().setText(scoreModel.getDatePlayed().toLocaleString().substring(0, 11));
    }

    @Override
    public int getItemCount() {
        return scoreModelList != null ? scoreModelList.size() : 0;
    }

    public void setItems(List<Score> scoreModels) {
        this.scoreModelList = scoreModels;
        notifyDataSetChanged();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView txtGoalsHome, txtGoalsAway, txtDate;


        public ScoreViewHolder(View itemView) {
            super(itemView);
            txtGoalsHome = (TextView) itemView.findViewById(R.id.txtGoalsHome);
            txtGoalsAway = (TextView) itemView.findViewById(R.id.txtGoalsAway);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        }

        public TextView getTxtGoalsHome() {
            return txtGoalsHome;
        }

        public TextView getTxtGoalsAway() {
            return txtGoalsAway;
        }

        public TextView getTxtDate() {
            return txtDate;
        }
    }
}
