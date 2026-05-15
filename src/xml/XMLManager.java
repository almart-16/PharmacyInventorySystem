package xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import pojos.Medication;
import pojos.Supplier;

public class XMLManager {

	/**
	 * Exports a list of medications to an XML file.
	 * 
	 * @param medications the list of medications
	 * @param fileName    the output file name
	 */
	public void exportMedications(List<Medication> medications, String fileName) {
		try {

			JAXBContext context = JAXBContext.newInstance(MedicationList.class);
			Marshaller marshaller = context.createMarshaller();

			// xml legible
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Metemos la lista de la base de datos en nuestro Wrapper
			MedicationList medicationList = new MedicationList(medications);

			marshaller.marshal(medicationList, new File(fileName));

			System.out.println("Medications exported successfully to " + fileName);

		} catch (Exception e) {
			System.err.println("Error exporting medications: " + e.getMessage());
		}
	}

	/**
	 * Imports medications from an XML file.
	 * 
	 * @param fileName the XML file name
	 * @return the list of imported medications
	 */
	public List<Medication> importMedications(String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(MedicationList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			MedicationList medicationList = (MedicationList) unmarshaller.unmarshal(new File(fileName));

			System.out.println("Medications imported successfully from " + fileName);
			return medicationList.getMedications();

		} catch (Exception e) {
			System.err.println("Error importing medications: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Exports a list of suppliers to an XML file.
	 * 
	 * @param suppliers the list of suppliers
	 * @param fileName  the output file name
	 */
	public void exportSuppliers(List<Supplier> suppliers, String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(SupplierList.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			SupplierList supplierList = new SupplierList(suppliers);

			marshaller.marshal(supplierList, new File(fileName));

			System.out.println("Suppliers exported successfully to " + fileName);

		} catch (Exception e) {
			System.err.println("Error exporting suppliers: " + e.getMessage());
		}
	}

	/**
	 * Imports suppliers from an XML file.
	 * 
	 * @param fileName the XML file name
	 * @return the list of imported suppliers
	 */
	public List<Supplier> importSuppliers(String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(SupplierList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			SupplierList supplierList = (SupplierList) unmarshaller.unmarshal(new File(fileName));

			System.out.print("Suppliers imported successfully from " + fileName);
			return supplierList.getSuppliers();
		} catch (Exception e) {
			System.err.println("Error importing suppliers: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Exports the whole database to an XML file.
	 * 
	 * @param project  the PharmacyWrapper containing all data
	 * @param fileName the output file name
	 */
	public void exportWholeDatabase(PharmacyWrapper project, String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(PharmacyWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			/*
			 * marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
			 * "<!DOCTYPE pharmacy_unit SYSTEM \"pharmacy_system.dtd\">");
			 */
			marshaller.marshal(project, new File(fileName));
			System.out.println("Whole database exported to " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports the whole database from an XML file.
	 * 
	 * @param fileName the XML file name
	 * @return the PharmacyWrapper containing all imported data
	 */
	public PharmacyWrapper importWholeDatabase(String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(PharmacyWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PharmacyWrapper) unmarshaller.unmarshal(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Validates an XML file against its XSD schema.
	 * 
	 * @param xmlFile the path to the XML file
	 * @param xsdFile the path to the XSD file
	 */
	public void validateXML(String xmlFile, String xsdFile) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdFile));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(new File(xmlFile)));
			System.out.println(
					"Validation successful: The XML file '" + xmlFile + "' is valid against '" + xsdFile + "'.");
		} catch (Exception e) {
			System.err.println("Validation failed: The XML file is NOT valid.");
			System.err.println("Reason: " + e.getMessage());
		}
	}

}
