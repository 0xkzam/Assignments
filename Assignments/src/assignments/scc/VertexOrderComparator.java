/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package assignments.scc;

import java.util.Comparator;

/**
 *
 * @author Kasun Amarasena
 * 
 * Order the vertices reversely by the value of Label of the Vertex
 * 
 */
public class VertexOrderComparator implements Comparator<Vertex> {
    
    @Override
    public int compare(Vertex v1,Vertex v2) throws IllegalStateException{
        int label1 = v1.getLabel();
        int label2 = v2.getLabel();
        if(label1 == label2){
            throw new IllegalStateException("Labels are equal! "
                    + "(label="+label1+")"+"( v1="+v1+", v2="+v2+")");
        }        
        return label2 - label1;
    }    
}
