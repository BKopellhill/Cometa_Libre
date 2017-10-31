package com.bkopellhill.adrian.cometa_libre;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

// Agregados para version "Java"
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import java.math.*;
import java.util.Locale;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public double ingreso, resultado;
    final double porcomis=0.11;
    final double comis=1.0-porcomis;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        /*  --- Borrar !!! ---
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        */

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    BasicFragment basicFragment=new BasicFragment();
                    return basicFragment;
                case 1:
                    AdvancedFragment advancedFragment=new AdvancedFragment();
                    return advancedFragment;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Modo Básico";
                case 1:
                    return "Modo Avanzado";
            }
            return null;
        }

    }


    // Copiado desde Comisión Libre

    public void calculaResultado(View visresul){
        CalcResul();
    }

    public void limpiaCampos(View vista){

        limpiaIngreso();
        limpiaResultado();

        // Instancio un objeto de la clase TextView para luego setearle la propiedad Invisible
        // Por el momento dejo comentadas ambas lineas (funcionan ok!)
        // TextView botonLimpia=(TextView) findViewById(R.id.btn_limpiar);
        // botonLimpia.setVisibility(View.INVISIBLE);
    }

    public void limpiaIngreso(){ // Esto era mostrarResultado()
        // Creamos una instancia perteneciente a la clase TextView
        // El objeto textoResultado es de tipo TextView y recibe la que encuentra (...)
        // en la vista que busca segun su Id
        TextView textoIngreso=(TextView) findViewById(R.id.edtxtInput);
        textoIngreso.setText("");
    }

    public void limpiaResultado(){ // Esto era mostrarResultado()
        TextView textoResultado=(TextView) findViewById(R.id.edtxt_resul);
        textoResultado.setText("");

    }

    public void CalcResul(){ // Esto era mostrarResultado()

        // Instancio una variable del tipo EditText
        EditText numero_ingresado=(EditText) findViewById(R.id.edtxtInput);

        // Instancio una variable del tipo TextView
        TextView textoIngreso=(TextView) findViewById(R.id.edtxt_resul);

        // Guardo en MResult el contenido de lo ingresado en txt_ingreso
        String MResult=numero_ingresado.getText().toString() ;

        if ( (MResult != null) && (!MResult.equals("")) ) {
            // Realizo la operación Matemática
            ingreso=Double.parseDouble(MResult);
            resultado=ingreso/comis;

            // Comienzo del metodo mas recomendable
            // instancio una variable del tipo BigDecimal (clase Math)
            BigDecimal bdr=BigDecimal.valueOf(resultado);
            // paso a una variable del tipo doble el resultado del redondeo con 2 decimales del resultado
            double rf=bdr.setScale(2,RoundingMode.HALF_UP).doubleValue();
            // Fin del metodo mas recomendable

            // Nuevo: Convierto a String para evitar la concatenación (sugerencia de Android Studio)
            // Antes usaba la siguiente linea, funcionaba bien pero en numeros grandes me mostraba "E" a la x.
            // String srf=String.valueOf(rf);
            // La reemplacé por la siguiente linea y me funcionó bien.
            // Tuve que forzarle el "Locale.GERMANY" para que me muestre el formato xxx.xxx,xx
            String srf=String.format(Locale.GERMANY,"%1$,.2f", rf);
            textoIngreso.setText(srf);
        } else {
            // textoIngreso.setText("Debes ingresar un numero para calcular su resultado");
            Toast.makeText(this, "Debes ingresar un número para calcular su resultado", Toast.LENGTH_SHORT).show();
        }

        // Para ocultar el teclado numerico cuando presiono el boton Calcular
        // Primero instancio que mi metodo de entrada es el teclado
        InputMethodManager miteclado=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // Luego oculto el teclado de la siguiente forma
        miteclado.hideSoftInputFromWindow(numero_ingresado.getWindowToken(),0);

    }

}
