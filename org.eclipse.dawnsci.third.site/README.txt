
1. Create the site here by getting third jars into the target platform and referencing them in the feature.
   This can be done for instance by adding a folder to the target with plugins and features folders.
2. Press build-all on the site.xml.
3. Deploy the site to public url using scp:
       scp -r org.eclipse.dawnsci.third.site/* www.opengda.org:/var/www/html/updates/tp_dawnsci/trunk
       

Using the buggy target editor:
1. When using edit, wait for it to figure out bundles you already have and do not remove them.
2. It is possible to edit its XML directly for speed, this sometimes does not then load in the target editor even though it is correct.
   - When editing check version is on orbit
   - When reloading in the target editor, HIT RELOAD to get those bundles downloaded.
   - Then save and set target platform again.
3. Figuring out and refreshing the target is slow because mars in in the target.