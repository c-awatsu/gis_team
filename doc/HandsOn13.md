## DBから取り出した座標の値を元にマーカーをセットする
これまでは、クリックした位置などにマーカーをセットしていました。  
今回はDBから持ってきた座標をもとにマーカーをセットします。  

まずDBを動かすための設定フォルダを作成します。  
`java`パッケージ内に`repository`パッケージ、`service`パッケージ、`dao`パッケージを作成してください。  

次に`resources`パッケージ内に`hikari.properties`ファイルを作成してください。  
その後[hikari.properties](/src/main/resources/hikari.properties)の中身を`hikari.properties`にコピペしてください。

次に`repository`パッケージ内に`IDBCP`インターフェースと`DBCP`クラスを作成してください。  
その後[IDBCP](/src/main/java/repository/IDBCP.java)と[DBCP](/src/main/java/repository/DBCP.java)の中身をそれぞれコピペしてください。

次に`java`パッケージ内に`Bean`パッケージを作成し、`ContributionBean`クラスを作成し内容は以下のようにしてください。

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

次に`dao`パッケージ内に`AbstractDAO`クラスと`IContributionDAO`インターフェースと`ContributionDAO`クラスを作成してください。
その後[AbstractDAO](/src/main/java/dao/AbstractDAO.java)の中身を`AbstractDAO`にコピーしてください。

```java

```


