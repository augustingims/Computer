
      // init fullscreen based on user pref
      var fullscreen = readCookie("fullscreen");
      if (fullscreen != 0) {
        if (fullscreen == "false") {
          toggleFullscreen(false);
        } else {
          toggleFullscreen(true);
        }
      }
      // init nav version for mobile
      if (isMobile) {
        swapNav(); // tree view should be used on mobile
        $('#nav-swap').hide();
      } else {
        chooseDefaultNav();
        if ($("#nav-tree").is(':visible')) {
          init_default_navtree("../../../");
        }
      }
      // scroll the selected page into view
      $(document).ready(function() {
        scrollIntoView("packages-nav");
        scrollIntoView("classes-nav");
        });
    </script>

     



<div class="col-12"  id="doc-col">

<div id="api-info-block">



  
   
  
  
  
  

  
   
  
  
  
  


<div class="sum-details-links">

Summary:









  <a href="#pubctors">Ctors</a>
  



  &#124; <a href="#pubmethods">Methods</a>
  



  &#124; <a href="#inhmethods">Inherited Methods</a>

&#124; <a href="#" onclick="return toggleAllClassInherited()" id="toggleAllClassInherited">[Expand All]</a>

</div><!-- end sum-details-links -->
<div class="api-level">
  
  Added in <a href="../../../guide/topics/manifest/uses-sdk-element.html#ApiLevels">API level 1</a>
  
  

</div>
</div><!-- end api-info-block -->


<!-- ======== START OF CLASS DATA ======== -->

<div id="jd-header">
    public
     
     
    
    class
<h1 itemprop="name">PrintWriterPrinter</h1>



  
    extends <a href="../../../reference/java/lang/Object.html">Object</a><br/>
  
  
  

  
  
      implements 
      
        <a href="../../../reference/android/util/Printer.html">Printer</a> 
      
  
  


    


</div><!-- end header -->

<div id="naMessage"></div>

<div id="jd-content" class="api apilevel-1">
<table class="jd-inheritance-table">


    <tr>
         	
        <td colspan="2" class="jd-inheritance-class-cell"><a href="../../../reference/java/lang/Object.html">java.lang.Object</a></td>
    </tr>
    

    <tr>
        
            <td class="jd-inheritance-space">&nbsp;&nbsp;&nbsp;&#x21b3;</td>
         	
        <td colspan="1" 