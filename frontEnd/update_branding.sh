#!/bin/bash

# AutoSpareHub Brand Update Script
# This script updates all HTML files to change PartsLK branding to AutoSpareHub

# Define the pages to update
pages=(
    "404.html"
    "about-us.html" 
    "add-item.html"
    "add-shop.html"
    "admin-controller.html"
    "cart.html"
    "checkout.html"
    "compare.html"
    "contact.html"
    "item-right-sidebar.html"
    "login-page.html"
    "my-account.html"
    "my-shop.html"
    "places.html"
    "register-page.html"
    "single-product-sale.html"
    "wishlist.html"
)

echo "Starting AutoSpareHub branding update..."

# Update each page
for page in "${pages[@]}"; do
    if [ -f "pages/$page" ]; then
        echo "Updating $page..."
        
        # Update title tags
        sed -i 's/<title>PartsLK.*<\/title>/<title>AutoSpareHub - Premium Auto Parts<\/title>/g' "pages/$page"
        sed -i 's/<title>PartsLk.*<\/title>/<title>AutoSpareHub - Premium Auto Parts<\/title>/g' "pages/$page"
        
        # Update alt tags for logos
        sed -i 's/alt="partsLk Logo"/alt="AutoSpareHub Logo"/g' "pages/$page"
        sed -i 's/alt="partslk Logo"/alt="AutoSpareHub Logo"/g' "pages/$page"
        sed -i 's/alt="partsLK Footer Logo"/alt="AutoSpareHub Footer Logo"/g' "pages/$page"
        
        # Update email addresses
        sed -i 's/info@parts.lk/info@autosparehub.lk/g' "pages/$page"
        sed -i 's/info@partslk.lk/info@autosparehub.lk/g' "pages/$page"
        
        # Add custom CSS reference (after the main style.css)
        sed -i 's|<link rel="stylesheet" href="../uren/assets/css/style.css">|<link rel="stylesheet" href="../uren/assets/css/style.css">\n    <!-- AutoSpareHub Custom Styles -->\n    <link rel="stylesheet" href="../assets/css/autosparehub-custom.css">|g' "pages/$page"
        
        echo "$page updated successfully!"
    else
        echo "Warning: $page not found"
    fi
done

echo "AutoSpareHub branding update completed!"