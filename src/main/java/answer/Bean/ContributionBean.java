package answer.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.postgresql.geometric.PGcircle;

import java.io.Serializable;
import java.time.LocalDateTime;

//Dataアノテーションをつけると自動でgetter,setterが生成される
@Data
//AllArgsConstructorをつけると自動で全てのクラス変数を引数にしたコンストラクターが生成される
@AllArgsConstructor
public class ContributionBean implements Serializable {
    private static final long serialVersionUID = -1649654287922384729L;

    //PGcircle型はpostgresのcircle型をJava上で扱うための型です
    //PGcircle型は緯度,軽度,その緯度軽度を中心にした円の半径で構成されます
    private PGcircle latlon;
    private LocalDateTime postTime;

    public ContributionBean(){
        this.latlon = new PGcircle(0d,0d,0);
        this.postTime = LocalDateTime.MIN;
    }

}
