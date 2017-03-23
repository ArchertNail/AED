package aed.AccesoFicheros;

import javafx.event.ActionEvent;

public class MostrarController {
	
	private MostrarFicheroView mFV;
	private AccesoFicherosModelo aFM;
	
	public MostrarController() {
		
		this.mFV = new MostrarFicheroView();
		this.aFM = new AccesoFicherosModelo();
		this.aFM.getRutaFicheroMostrar().bind(mFV.getRutaText().textProperty());
		bind();
	}
	
	public void bind(){
		this.mFV.getMostrarFicheros().setOnAction(e->onMostrarFicheros(e));
	}
	private void onMostrarFicheros(ActionEvent e) {
		this.mFV.getListarFicheros().textProperty().set(aFM.ListarFicherosRuta());
	}

	public MostrarFicheroView getRootMostrar(){
		return mFV;
	}
}
