## DBから取り出した座標の値を元にマーカーをセットする
これまでは、クリックした位置などにマーカーをセットしていました。  
今回はDBから持ってきた座標をもとにマーカーをセットします。  

まずDBを動かすための設定フォルダを作成します。  
`java`パッケージ内に`repository`パッケージ、`service`パッケージ、`dao`パッケージを作成してください。  

次に`resources`パッケージ内に`hikari.properties`ファイルを作成してください。  
その後[hikari.properties](/src/main/resources/hikari.properties)の中身を`hikari.properties`にコピペしてください。