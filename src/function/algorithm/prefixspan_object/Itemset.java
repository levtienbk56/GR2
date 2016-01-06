package function.algorithm.prefixspan_object;
/* This file is copyright (c) 2008-2013 Philippe Fournier-Viger
* 
* This file is part of the SPMF DATA MINING SOFTWARE
* (http://www.philippe-fournier-viger.com/spmf).
* 
* SPMF is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* SPMF is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* SPMF. If not, see <http://www.gnu.org/licenses/>.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * This class represents an itemset for the BIDE+ and PrefixSpan that takes sequences of strings
 * as input.
 * 
 * @see AlgoPrefixSpan_with_Strings
 * @see AlgoBIDEPlus_withStrings
* @author Philippe Fournier-Viger
 */
public class Itemset<T>{

	private final List<T> items = new ArrayList<T>(); // ordered list.
	
	public Itemset(T item){
		addItem(item);
	}
	
	public Itemset(){
	}

	public void addItem(T value){
		if(!items.contains(value)){
			items.add(value);
		}
	}
	
	public List<T> getItems(){
		return items;
	}
	
	public T get(int index){
		return items.get(index);
	}
	
	public void print(){
		System.out.print(toString());
	}
	
	public String toString(){
		StringBuilder r = new StringBuilder ();
		for(T attribute : items){
			r.append(attribute.toString());
			r.append(' ');
		}
		return r.toString();
	}

	
	public int size(){
		return items.size();
	}
	
	public Itemset<T> cloneItemSetMinusItems(Map<T, Set<Integer>> mapSequenceID, double minsuppRelatif) {
		Itemset<T> itemset = new Itemset<T>();
		for(T item : items){
			if(mapSequenceID.get(item).size() >= minsuppRelatif){
				itemset.addItem(item);
			}
		}

		return itemset;
	}
	
	public Itemset<T> cloneItemSet(){
		Itemset<T> itemset = new Itemset<T>();
		itemset.getItems().addAll(items);
		return itemset;
	}
}
