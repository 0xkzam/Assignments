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

import java.util.Objects;

/**
 *
 * @author Kasun Amarasena
 *
 * Edge data structure for graphs
 * 
 */
public class Edge {

    private final Vertex source, dest;

    /**
     *
     * Construct an Edge between 2 vertices
     *
     * @param source - Source Vertex
     * @param dest - Destination Vertex
     */
    public Edge(Vertex source, Vertex dest) {
        this.source = source;
        this.dest = dest;
    }

    /**
     * @return Destination Vertex
     */
    public Vertex getDestination() {
        return dest;
    }

    /**     
     * @return Source Vertex
     */
    public Vertex getSource() {
        return source;
    }

    @Override
    public String toString() {
        return source.getName() + "->" + dest.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Edge) {
            Edge e = (Edge) o;
            return this.source.equals(e.getSource()) && this.dest.equals(e.getDestination());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.source);
        hash = 79 * hash + Objects.hashCode(this.dest);
        return hash;
    }

}
