## DBから取り出した座標の値を元にマーカーをセットする
これまでは、クリックした位置などにマーカーをセットしていました。  
今回はDBから持ってきた座標をもとにマーカーをセットします。  

まずDBを動かすための設定フォルダを作成します。  
`java`パッケージ内に`answer.repository`パッケージ、`answer.service`パッケージ、`answer.dao`パッケージを作成してください。  

次に`resources`パッケージ内に`hikari.properties`ファイルを作成してください。  
その後[hikari.properties](/src/main/resources/hikari.properties)の中身を`hikari.properties`にコピペしてください。

次に`answer.repository`パッケージ内に`IDBCP`インターフェースと`DBCP`クラスを作成してください。  
その後[IDBCP](/answer/repository/IDBCP.java)と[DBCP](/answer/repository/DBCP.java)の中身をそれぞれコピペしてください。

次に`java`パッケージ内に`answer.Bean`パッケージを作成し、`ContributionBean`クラスを作成し内容は以下のようにしてください。

```java
package Bean;

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
```

次に`answer.dao`パッケージ内に`AbstractDAO`クラスと`IContributionDAO`インターフェースと`ContributionDAO`クラスを作成してください。
その後[AbstractDAO](/answer/dao/AbstractDAO.java)の中身を`AbstractDAO`にコピーしてください。

`IContributionDAO`インターフェースの内容は以下のようにしてください。
```java
package dao;

import Bean.ContributionBean;
import com.google.inject.ImplementedBy;

import java.util.List;

//とりあえずおまじない
//気になる人はgoogle juiceとかDIコンテナとかでググってください
@ImplementedBy(ContributionDAO.class)
public interface IContributionDAO {
    List<ContributionBean> selectContributionList();
}
```

`ContributionDAO`クラスの内容は以下のようにしてください。
```java
package dao;

import Bean.ContributionBean;
import org.sql2o.Connection;

import java.sql.SQLException;
import java.util.List;

public class ContributionDAO extends AbstractDAO implements IContributionDAO {
    @Override
    public List<ContributionBean> selectContributionList() {
        List<ContributionBean> contributionBeanList = null;
        String sql = "select latlon, post_time from contribution where available = 'true'";
        try(Connection conn = dbcp.getSql2o().open()){
            contributionBeanList = conn.createQuery(sql)
                    .setAutoDeriveColumnNames(true)//Beanの変数名とDBのカラム名をキャメルケースとスネークケースの差を吸収してマッピングしてくれる
                    .executeAndFetch(ContributionBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contributionBeanList;
    }
}

```

次に`answer.service`パッケージ内に`IContoributionService`インターフェースと`ContoributionService`クラスを作成してください。

`IContributionService`は以下の内容にしてください。
```java

```
