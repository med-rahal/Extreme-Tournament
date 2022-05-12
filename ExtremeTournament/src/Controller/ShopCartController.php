<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ShopCartController extends AbstractController
{
    /**
     * @Route("/cart", name="app_shop_cart")
     */
    public function index(): Response
    {
        return $this->render('shop_cart/index.html.twig', [
            'controller_name' => 'ShopCartController',
        ]);
    }
}
