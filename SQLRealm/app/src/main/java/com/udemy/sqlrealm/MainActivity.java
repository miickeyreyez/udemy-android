package com.udemy.sqlrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.udemy.sqlrealm.application.MyAdapter;
import com.udemy.sqlrealm.models.Dog;
import com.udemy.sqlrealm.models.Person;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Person>>
{

    private ListView listView;
    private MyAdapter adapter;
    private Realm realm;
    private RealmResults<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        listView = (ListView) findViewById(R.id.listView);
        people = getAllPeople();
        people.addChangeListener(this);
        adapter = new MyAdapter(this,R.layout.list_item_view,people);
        listView.setAdapter(adapter);
    }

    @Override
    public void onChange(RealmResults<Person> element)
    {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy()
    {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.item_add:
                addPeople();
                return true;
            case R.id.item_delete:
                removeAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private RealmResults<Person> getAllPeople()
    {
        return realm.where(Person.class).findAll();
    }

    private void removeAll()
    {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private void addPeople()
    {
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                Dog dog1 = new Dog("Scooby Doo");
                Dog dog2 = new Dog("Snoopy");
                Dog dog3 = new Dog("Brian");
                Dog dog4 = new Dog("Ayudante de santa");
                Dog dog5 = new Dog("Pluto");

                Person person1 = new Person("Shaggy");
                Person person2 = new Person("Charlie Brown");
                Person person3 = new Person("Peter Griffin");
                Person person4 = new Person("Bart Simpson");
                Person person5 = new Person("Goofy");

                person1.getDogs().add(dog1);
                person2.getDogs().add(dog2);
                person3.getDogs().add(dog3);
                person4.getDogs().add(dog4);
                person5.getDogs().add(dog5);

                realm.copyToRealmOrUpdate(dog1);
                realm.copyToRealmOrUpdate(dog2);
                realm.copyToRealmOrUpdate(dog3);
                realm.copyToRealmOrUpdate(dog4);
                realm.copyToRealmOrUpdate(dog5);

                realm.copyToRealmOrUpdate(person1);
                realm.copyToRealmOrUpdate(person2);
                realm.copyToRealmOrUpdate(person3);
                realm.copyToRealmOrUpdate(person4);
                realm.copyToRealmOrUpdate(person5);

                people = getAllPeople();
            }
        });
    }
}
