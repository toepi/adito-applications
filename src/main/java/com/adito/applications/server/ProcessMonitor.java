
				/*
 *  Adito
 *
 *  Copyright (C) 2003-2006 3SP LTD. All Rights Reserved
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
			
package com.adito.applications.server;
import java.io.OutputStream;

public class ProcessMonitor {

  String name;
  Process process;

  IOStreamConnector stdout;
  IOStreamConnector stderr;

  public ProcessMonitor(String name,
                        Process process) {

    this.process = process;
    this.name = name;
  }


  public String getName() {
    return name;
  }

  /**
   * Watch and wait until the process has completed. Any output
   * from the process will be written to the streams provided and
   * the exit code will be returned when the process terminates.
   *
   * @param out an OutputStream to receive data from the stdout stream
   * @param err an OutputStream to receive data from the stderr stream

   * @return
   * @throws java.lang.InterruptedException
   */
  public int watch(OutputStream out,
                   OutputStream err)
          throws InterruptedException {

    stdout = new IOStreamConnector();
    stdout.connect(process.getInputStream(), out);

    stderr = new IOStreamConnector();
    stderr.connect(process.getErrorStream(), err);

    return process.waitFor();
  }

  /**
   * Terminate the process
   */
  public void kill() {
    process.destroy();
  }





}
