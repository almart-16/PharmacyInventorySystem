package interfaces;

import Pojos.Purchase;

public interface PurchaseManager {

	//guardar una compra de un medicamento en la base de datos
	boolean savePurchase(Purchase purchase);
	
	//se registra la venta, se reduce el stock en el inventario, y se guarda la compra
	// CUANDO HAGÁIS ESTE MÉTODO, COMPROBAR SI EL MEDICAMENTO NECESITA RECETA O NO
	boolean sellMedication(Purchase purchase);
	
	
}
