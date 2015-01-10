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

/**
 *
 * @author Kasun Amarasena
 * 
 * Data structure for Vertex representation
 */
public class Vertex {
    
    private final String name;
    private int label;
    
    /**
     *
     * @param name String
     */
    public Vertex(String name) {
        this.name = name;
        this.label = -1;
    }
    
    /**
     *
     * @param name Integer
     */
    public Vertex(Integer name) {
        this.name = String.valueOf(name);
        this.label = -1;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Value(Label) obtained from Topological Ordering  
     * @param label integer value of the relevant position
     */
    public void setLabel(int label){
        this.label = label;
    }
    
    /** 
     * @return the Label obtained from the Topological ordering
     */
    public int getLabel(){
        return this.label ;
    }
    
        
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        if (o instanceof Vertex) {
            Vertex v = (Vertex) o;
            return this.name.equals(v.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;       
        hash = 89 * hash + this.name.hashCode();
        return hash;
    }
}
