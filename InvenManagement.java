import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class InvenManagement {

	public static void main(String[] args) throws Exception {
		System.out.println("Please enter input to proceed... End input with #");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		TreeMap<String,Inventory> items = new TreeMap<String,Inventory>();
		double currProfit=0,prevProfit=0,totalValue=0;
		Inventory item=null;
		while(!"#".equals((line=br.readLine())))
		{
			String inputs[]=line.trim().split(" ");
			switch(inputs[0])
			{
				case "create": 	
								items.put(inputs[1], new Inventory(inputs[1],Double.parseDouble(inputs[2]),Double.parseDouble(inputs[3])));
								break;
				case "delete":	
								item=items.get(inputs[1]);
								currProfit-=item.getCostPrice()*item.getQuantity();
								items.remove(inputs[1]);
								
								break;
				case "updateBuy":	
								item=items.get(inputs[1]);
								item.setQuantity(item.getQuantity()+Integer.parseInt(inputs[2]));
								items.put(inputs[1], item);
								break;
				case "updateSell":	
								item=items.get(inputs[1]);
								currProfit+=(item.getSellingPrice()-item.getCostPrice())*Integer.parseInt(inputs[2]);
								item.setQuantity(item.getQuantity()-Integer.parseInt(inputs[2]));
								items.put(inputs[1], item);
								break;	
				case "updateSellPrice":	
								item=items.get(inputs[1]);
								currProfit+=(item.getSellingPrice()-item.getCostPrice())*Integer.parseInt(inputs[2]);
								item.setQuantity(item.getQuantity()-Integer.parseInt(inputs[2]));
								items.put(inputs[1], item);
								break;	
				case "report":
								
								Iterator<Map.Entry<String,Inventory>> invIterator=items.entrySet().iterator();
								totalValue=0;
								System.out.println("\t\tINVENTORY REPORT");
								System.out.println("Item Name 	Bought At    	Sold At       	AvailableQty    	Value");
								System.out.println("--------- 	---------    	-------       	-----------     	-------");
								DecimalFormat df = new DecimalFormat("###########.##");
								while(invIterator.hasNext())
								{
									Map.Entry<String,Inventory> me=invIterator.next();
									Inventory in=me.getValue();
									totalValue+=in.getCostPrice()*in.getQuantity();
									System.out.println(in.getItemName()+"      \t"+df.format(in.getCostPrice())+"\t\t"+df.format(in.getSellingPrice())+"\t\t"+in.getQuantity()+"\t\t\t"+df.format(in.getCostPrice()*in.getQuantity()));
								}
								System.out.println("---------------------------------------------------------------------------");
								System.out.println("Total value                                                     	"+df.format(totalValue));
								System.out.println("Profit since previous report                                      "+df.format(currProfit));
								prevProfit=currProfit;
								currProfit=0;
								break;
								
			}                 	
		}

	}

}
