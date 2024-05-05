# Rapport
Det första steget var, likt tidigare, att skapa en fork av projektet på LenaSYS Github. Först ut var likt tidigare att fixa så att applikationen gick att kompilera genom att uppdatera "gradle". Nästa steg var att förebereda för preferenser i applikationen genom att deklarera variables tillsamans med intialisera dem. 

**MainActivity.java**
```
    ...
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;
    ...
    // Get my preference
    myPreferenceRef = getPreferences(MODE_PRIVATE);
    myPreferenceEditor = myPreferenceRef.edit();
    ...
```

Därefter behövdes informationen från preferenserna visas på skärmen vilket gjordes först genom att uppdatera _activity_main.xml_ med _textView_'s. Dessa användes sedan för att visa informationen på skärmen.  

**MainActivity.java**
```
    // Read from preferences
    TextView prefTextView = findViewById(R.id.prefText);
    prefTextView.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found..."));
```

Efter att värdet visades på skärmen implementerades en andra aktivtet som med hjälp av en knapp tryckning på startskärmen startar den andra aktiviteten. Utöver den tomma klassen, fick _SecondActivity.java_ en layout som särskiljar den från första aktiviteten. I _MainActivity.java_ implementerades också en _onClickListener_ som användes för att starta den andra aktiviteten. 

**AndroidManifest.xml**
```
    <activity
        android:name=".SecondActivity"
        android:theme="@style/AppTheme.NoActionBar"/>
```

**MainActivity.java**
```
    // Get button and add event listener
    Button btn = findViewById(R.id.firstBtn);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    });

```

Nästa steg var sedan att implementera en sätt för att användaren att återvända till första aktiviteten. Likt tidigare, implementerades en _onClickListener_ på en annan knapp.

**SecondActivity.java**
```
    // Get button and add event listener
    Button btn = findViewById(R.id.secondBtn);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
```

Slutligen, var det dags att uppdatera värdet som finns lagrat i delade preferenser. Uppdatering av värdet lades till i _onClickListener_ i _SecondActivity.java_. I _SecondActivity_ finns det också två privata variabler som lagrar de delade preferenserna samt editor. För att därefter hämtas i första aktivitetens _onResume_ för att uppdatera texten som visas på skärmen.

**SecondActivity.java**
```
    // Get ref to preference
    myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
    myPreferenceEditor = myPreferenceRef.edit();

    // Store new preference
    myPreferenceEditor.putString("MyAppPreferenceString", "Seems like things have changed here");
    myPreferenceEditor.apply();
```

**MainActivity.java**
```
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
        SharedPreferences.Editor myPreferenceEditor = myPreferenceRef.edit();

        // Read from preferences
        TextView prefTextView = findViewById(R.id.prefText);
        prefTextView.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found..."));
    }
```

Pågrund av någon bugg eller problem så återställdes inte värdet på de delade preferenserna när programmet startades om. Det fick lösas genom att i koden _MainActivity.java_ _onCreate_ behövdes uppdateras för att sätta in ett start värde.

**MainActivity.java**
```
    // Get preference
    myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
    myPreferenceEditor = myPreferenceRef.edit();

    // Store initial preference
    myPreferenceEditor.putString("MyAppPreferenceString", "An preference with only one value at start");
    myPreferenceEditor.apply();
```


Startskärmen (MainActivity). Notera texten som visas under strecket, denna text kommer att ändras när applikationens preferenser uppdateras.
![Screenshot_20240505_134228](https://github.com/a20gabpa/mobileapp-programming-preferences/assets/102604680/4a0ec9a4-ff76-4ef4-81c8-8e4de5289888)
Andra skärmen (SecondActivity), en ny layout som visar en knapp tillsammans med lite text.
![Screenshot_20240505_134237](https://github.com/a20gabpa/mobileapp-programming-preferences/assets/102604680/126cb49d-e825-4438-9a2d-27ad09ef29e0)
Startskärmen igen fast med uppdaterad text (MainActivity). 
![Screenshot_20240505_134245](https://github.com/a20gabpa/mobileapp-programming-preferences/assets/102604680/095db25e-e5d5-433b-9c23-a0f18766f10b)
