# token_provider

This plugin support share token inside SmartFactory system

## Usage

### Add this to AndroidManifest.xml

#### Host app (Smart Factory):

```xml
<manifest>
    <application>
        <provider
            android:name="com.hw.agile.token.provider.token_provider.TokenProvider"
            android:authorities="com.hw.agile.token.provider.token_provider.TokenProvider"
            android:readPermission="smartfactory.permission.READ_TOKEN"
            android:enabled="true"
            android:exported="true" />
    </application>

    <permission
        android:name="smartfactory.permission.READ_TOKEN"
        android:label="Read token"
        android:protectionLevel="normal"
        tools:ignore="ManifestOrder" />
</manifest>
```


#### onsumer app (OPPM, ESD, Paperless,...etc):

```xml
<manifest>
    <queries>
       <package android:name="com.foxconn.fii.app.smartfactory" /> <!--This is host app package-->
    </queries>
    <uses-permission android:name="smartfactory.permission.READ_TOKEN" />
</manifest>
```
